package com.example.deskly.Models

data class Desk(
    val id: Int,
    val officeId: String,
    val isReserved: Boolean,
    val isSelected: Boolean = false
)

