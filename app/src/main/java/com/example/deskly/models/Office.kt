package com.example.deskly.models

data class Office(
    val id: String,
    val name: String,
    val location: String,
    val deskLayout: List<Int>, // This could represent the grid layout of desks
)

var mockOffices: List<Office> = listOf(
    Office("OS", "Osijek", "Osijek", listOf(1, 1, 1, 1, 1)),
    Office(id = "SP", name = "Split", location = "Split", listOf(1, 1, 1, 1, 1)),
    Office(id = "ZG", name = "Zagreb", location = "Beograd", listOf(1, 1, 1, 1, 1)),
    Office(id = "RI", name = "Rijeka", location = "Novi Sad", listOf(1, 1, 1, 1, 1)),
)


