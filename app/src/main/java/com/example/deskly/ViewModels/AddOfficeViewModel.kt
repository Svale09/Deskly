package com.example.deskly.ViewModels

import androidx.lifecycle.ViewModel
import com.example.deskly.Models.Desk
import com.example.deskly.Models.Office

class AddOfficeViewModel: ViewModel() {
    fun addOffice(office: Office, desks: List<Desk>) {
        //TODO Add office to database
    }

    fun addDesk(desk: Desk) {
        //TODO Add desk to grid
    }

    fun removeDesk(){
        //TODO Remove desk from grid
    }
}