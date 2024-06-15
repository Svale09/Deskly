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
}
