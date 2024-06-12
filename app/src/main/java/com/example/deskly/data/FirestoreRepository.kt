package com.example.deskly.data

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
}
