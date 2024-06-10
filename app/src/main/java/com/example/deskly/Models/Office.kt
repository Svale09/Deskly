package com.example.deskly.Models

data class Office(
    val id: String,
    val name: String,
    val location: String,
    val deskLayout: List<Int>, // This could represent the grid layout of desks
)

var mockOffices: List<Office> = listOf(Office("OS", "Osijek", "Osijek", listOf()))

