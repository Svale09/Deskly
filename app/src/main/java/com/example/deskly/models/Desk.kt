package com.example.deskly.models

import java.util.Date

data class Desk(
    val id: Int,
    val officeId: String,
    val isReserved: Boolean = false,
    val isSelected: Boolean = false,
    val reservedDates: List<Date>
)
