package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ReserveDeskViewModel : ViewModel() {

    private val _offices = MutableStateFlow<List<Office>>(emptyList())
    val offices: StateFlow<List<Office>> get() = _offices

    private val _desks = MutableStateFlow<List<Desk>>(emptyList())
    val desks: StateFlow<List<Desk>> get() = _desks

    private val _selectedOffice = MutableStateFlow<Office?>(null)
    val selectedOffice: StateFlow<Office?> get() = _selectedOffice

    private val _selectedDate = MutableStateFlow<String>("")
    val selectedDate: StateFlow<String> get() = _selectedDate

    private val _isReserveButtonEnabled = MutableStateFlow(false)
    val isReserveButtonEnabled: StateFlow<Boolean> get() = _isReserveButtonEnabled

    private val _selectedDeskId = MutableStateFlow<Int?>(null)
    val selectedDeskId: StateFlow<Int?> get() = _selectedDeskId

    fun loadOffices(offices: List<Office>) {
        _offices.value = offices
    }

    fun loadDesksForOffice(office: Office) {
        // Load desks logic here
    }

    fun selectDesk(deskId: Int) {
        _selectedDeskId.update { deskId }
        _isReserveButtonEnabled.value = _desks.value.any { it.id == deskId }
    }

    fun setOffice(officeId: String) {
        val selected = _offices.value.find { it.id == officeId }
        _selectedOffice.value = selected
        selected?.let { loadDesksForOffice(it) }
    }

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    fun reserveDesk() {
        // Handle the reservation logic
    }
}
