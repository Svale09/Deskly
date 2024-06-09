package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthenticationViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun logIn(email: String, password: String) = liveData {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit(result.user)
        } catch (e: Exception) {
            emit(null)
        }
    }

    fun signUp(email: String, password: String) = liveData {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            emit(result.user)
        } catch (e: Exception) {
            emit(null)
        }
    }

    fun logOut() {
        auth.signOut()
    }

    val currentUser get() = auth.currentUser
}