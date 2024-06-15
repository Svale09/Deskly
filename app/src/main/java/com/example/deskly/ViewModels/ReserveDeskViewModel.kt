package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Data.FirestoreRepository
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReserveDeskViewModel : ViewModel() {
    private val firestoreRepository = FirestoreRepository()

    private val _offices = MutableStateFlow<List<Office>>(emptyList())
    val offices: StateFlow<List<Office>> get() = _offices

    private val _desks = MutableStateFlow<List<Desk>>(emptyList())
    val desks: StateFlow<List<Desk>> get() = _desks

    private val _selectedOffice = MutableStateFlow<Office?>(null)
    val selectedOffice: StateFlow<Office?> get() = _selectedOffice

    private val _selectedDate = MutableStateFlow<String>("")
    val selectedDate: StateFlow<String> get() = _selectedDate

    private val _isReserveButtonEnabled = MutableStateFlow(false)

    private val _selectedDeskId = MutableStateFlow<Int?>(null)
    val selectedDeskId: StateFlow<Int?> get() = _selectedDeskId

    init {
        loadOffices()
    }

    fun loadOffices() {
        CoroutineScope(Dispatchers.IO).launch {
            _offices.value = firestoreRepository.fetchOffices()
        }
    }

    fun clearSelectedDesk(){
        _selectedDeskId.update { null }
    }

    fun loadDesksForOffice(officeName: String) {
        _offices.value.find { it.name == officeName }?.let { office ->
            _desks.value = office.desks
        }
    }

    fun selectDesk(deskId: Int) {
        _selectedDeskId.update { deskId }
        _isReserveButtonEnabled.value = _desks.value.any { it.id == deskId }
    }

    fun reserveDesk(officeName: String, deskId: Int, date: String) {
        CoroutineScope(Dispatchers.IO).launch {
            firestoreRepository.reserveDesk(officeName, deskId.toInt(), date)
        }
    }
}
