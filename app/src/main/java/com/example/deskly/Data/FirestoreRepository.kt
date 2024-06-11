package com.example.deskly.Data

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    val db = Firebase.firestore

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
            // Query the "users" collection for the document with the specified email
            val querySnapshot = db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .await()

            // If there's no matching document, return null
            if (querySnapshot.isEmpty) {
                null
            } else {
                // Extract the userRole from the first document
                val userRole = querySnapshot.documents[0].getLong("userRole")?.toInt()
                userRole
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}