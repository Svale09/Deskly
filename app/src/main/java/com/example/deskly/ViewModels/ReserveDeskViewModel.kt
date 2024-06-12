package com.example.deskly.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office

class ReserveDeskViewModel : ViewModel() {

    private val _offices = MutableLiveData<List<Office>>()
    val offices: LiveData<List<Office>> get() = _offices

    private val _desks = MutableLiveData<List<Desk>>()
    val desks: LiveData<List<Desk>> get() = _desks

    private val _selectedOffice = MutableLiveData<Office?>()
    val selectedOffice: LiveData<Office?> get() = _selectedOffice

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    private val _isReserveButtonEnabled = MutableLiveData(false)
    val isReserveButtonEnabled: LiveData<Boolean> get() = _isReserveButtonEnabled

    fun loadOffices(offices: List<Office>) {
        _offices.value = offices
    }

    fun loadDesksForOffice(office: Office) {
        /*_desks.value = office.deskLayout.mapIndexed { index, isDesk ->
            Desk(
                id = index,
                officeId = office.id,
                isReserved = isDesk == 1
            )
        }*/
    }

    fun selectDesk(deskId: Int) {
        _desks.value = _desks.value?.map {
            if (it.id == deskId) it.copy(isSelected = !it.isSelected) else it.copy(isSelected = false)
        }
        _isReserveButtonEnabled.value = _desks.value?.any { it.isSelected } == true
    }

    fun setOffice(officeId: String) {
        val selected = _offices.value?.find { it.id == officeId }
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

