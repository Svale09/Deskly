package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddOfficeViewModel: ViewModel() {
    private val _desks = MutableStateFlow<List<Desk>>(emptyList())
    val desks: StateFlow<List<Desk>> get() = _desks

    fun addOffice(office: Office, desks: List<Desk>) {
        //TODO Add office to database
    }

    fun addDesk(desk: Desk) {
        _desks.value += desk
    }

    fun removeDesk(){
        if (_desks.value.isNotEmpty()) {
            _desks.value = _desks.value.dropLast(1)
        }
    }
}