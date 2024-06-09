package com.example.deskly.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthenticationViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loginState = MutableLiveData<FirebaseUser?>()
    val loginState: LiveData<FirebaseUser?> get() = _loginState

    private val _signUpState = MutableLiveData<FirebaseUser?>()
    val signUpState: LiveData<FirebaseUser?> get() = _signUpState

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                _loginState.postValue(result.user)
            } catch (e: Exception) {
                _loginState.postValue(null)
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                _signUpState.postValue(result.user)
            } catch (e: Exception) {
                _signUpState.postValue(null)
            }
        }
    }

    fun logOut() {
        auth.signOut()
    }

    val currentUser get() = auth.currentUser
}