package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Data.FirestoreRepository
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddOfficeViewModel : ViewModel() {
    private val firestoreRepository = FirestoreRepository()

    private val _desks = MutableStateFlow<List<Desk>>(emptyList())
    val desks: StateFlow<List<Desk>> get() = _desks

    fun addOffice(officeName: String) {
        val office = Office(name = officeName, desks = emptyList())
        CoroutineScope(Dispatchers.IO).launch {
            firestoreRepository.addOffice(office, _desks.value) {
                // Clear the desks list only after the office has been successfully added
                _desks.value = emptyList()
            }
        }
    }

    fun addDesk(desk: Desk) {
        _desks.value += desk
    }

    fun removeDesk() {
        if (_desks.value.isNotEmpty()) {
            _desks.value = _desks.value.dropLast(1)
        }
    }
}