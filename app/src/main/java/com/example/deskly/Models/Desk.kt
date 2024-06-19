package com.example.deskly.Models

data class Desk(
    val id: Int,
    val officeId: String,
    val reservedDates: List<String>,
)

val mockDesks = listOf(
    Desk(1, "1",  listOf("13/06/2024", "14/06/2024", "15/06/2024")),
    Desk(2, "1",  listOf("13/06/2024", "15/06/2024")),
    Desk(3, "1",  listOf( "14/06/2024", "15/06/2024")),
    Desk(4, "1",  listOf("13/06/2024", "14/06/2024")),
    Desk(5, "1",  listOf("13/06/2024")),
    Desk(6, "1",  listOf("14/06/2024")),
    Desk(7, "1",  listOf("15/06/2024")),
    Desk(8, "1",  listOf()),
    Desk(9, "1",  listOf("16/06/2024")),
    Desk(10, "1",  listOf("20/06/2024")),
)

