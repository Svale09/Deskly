package com.example.deskly.Data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db = Firebase.firestore

    fun addNewUser(email: String, role: Int) {
        val user = hashMapOf(
            "email" to email,
            "role" to role
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    suspend fun fetchUserRoleByEmail(email: String): Int? {
        return try {
            // Query the "users" collection where the "email" field matches the provided email
            val querySnapshot = db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .await()

            // Check if any documents are returned
            if (querySnapshot.documents.isNotEmpty()) {
                // Get the first document (assuming emails are unique)
                val document = querySnapshot.documents[0]
                // Return the "role" field as an Integer
                document.getLong("role")?.toInt()
            } else {
                // If no documents found, return null
                null
            }
        } catch (e: Exception) {
            // Handle the exception
            e.printStackTrace()
            null
        }
    }

    fun addOffice(office: Office, desks: List<Desk>, onSuccess: () -> Unit) {
        val officeData = hashMapOf(
            "name" to office.name,
            "desks" to desks.map { desk ->
                hashMapOf(
                    "id" to desk.id,
                    "officeId" to desk.officeId,
                    "isReserved" to desk.isReserved,
                    "reservedDates" to desk.reservedDates
                )
            }
        )

        db.collection("offices")
            .add(officeData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Office added with ID: ${documentReference.id}")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding office", e)
            }
    }

    suspend fun fetchOffices(): List<Office> {
        return try {
            // Query the "offices" collection
            val querySnapshot = db.collection("offices").get().await()

            // Map the documents to Office objects
            querySnapshot.documents.map { document ->
                val name = document.getString("name") ?: ""
                val desks = (document.get("desks") as? List<Map<String, Any>>)?.map { deskData ->
                    Desk(
                        id = (deskData["id"] as Long).toInt(),
                        officeId = deskData["officeId"] as String,
                        reservedDates = deskData["reservedDates"] as List<String>
                    )
                } ?: emptyList()
                Office(name = name, desks = desks)
            }
        } catch (e: Exception) {
            // Handle the exception
            e.printStackTrace()
            emptyList()
        }
    }

    //    suspend fun reserveDesk(officeName: String, deskId: Int, date: String) {
//        try {
//            // Query the "offices" collection where the "name" field matches the provided officeName
//            val querySnapshot = db.collection("offices")
//                .get()
//                .await()
//
//            val officePathSnapshot = querySnapshot.documents.first {
//                it.getField<String>("name") == officeName
//            }
//
//            db.collection("offices").document(officePathSnapshot.id)
//                .collection("desks").document().update(
//                    deskId.toString(), ""
//                )
//
//        } catch (e: Exception) {
//            // Handle the exception
//            e.printStackTrace()
//        }
//    }
    suspend fun reserveDesk(officeName: String, deskId: Int, date: String) {
        try {
            // Query the "offices" collection where the "name" field matches the provided officeName
            val querySnapshot = db.collection("offices")
                .whereEqualTo("name", officeName)
                .get()
                .await()

            if (querySnapshot.documents.isNotEmpty()) {
                // Get the first document (assuming office names are unique)
                val officeDocument = querySnapshot.documents[0]
                val officeRef = officeDocument.reference

                // Retrieve the current desks
                val desks = officeDocument.get("desks") as? List<Map<String, Any>> ?: emptyList()

                // Find the desk with the matching deskId
                val deskIndex = desks.indexOfFirst { desk ->
                    val id = (desk["id"] as? Long)?.toInt() ?: desk["id"] as? Int
                    Log.d("xxx", "Checking desk with id: $id against deskId: $deskId")
                    id == deskId
                }

                if (deskIndex != -1) {
                    val desk = desks[deskIndex].toMutableMap()
                    val reservedDates = desk["reservedDates"] as? List<String> ?: emptyList()

                    // Append the new date
                    val updatedReservedDates = reservedDates + date
                    desk["reservedDates"] = updatedReservedDates

                    // Update the specific desk in the desks array
                    val updatedDesks = desks.toMutableList()
                    updatedDesks[deskIndex] = desk

                    // Update the desks field in the office document
                    officeRef.update("desks", updatedDesks).await()

                    Log.d(TAG, "Desk reserved successfully")
                } else {
                    Log.w(TAG, "No desk found with the ID: $deskId")
                }
            } else {
                Log.w(TAG, "No office found with the name: $officeName")
            }
        } catch (e: Exception) {
            // Handle the exception
            e.printStackTrace()
            Log.w(TAG, "Error reserving desk", e)
        }
    }
}
