package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddOfficeViewModel: ViewModel() {
    private val _desks = MutableStateFlow<List<Desk>>(emptyList())
    val desks: StateFlow<List<Desk>> get() = _desks
    private var currentDeskId = 0

    fun addOffice(office: Office, desks: List<Desk>) {
        //TODO Add office to database
    }

    fun addDesk(desk: Desk) {
        _desks.value += desk.copy(id = currentDeskId)
        currentDeskId++
    }

    fun removeDesk(){
        _desks.value = _desks.value.dropLast(1) //removes the last desk in the list
    }
}