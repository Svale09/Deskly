package com.example.deskly.Models

data class Office(
    val name: String,
    val desks: List<Desk>
)

var mockOffices: List<Office> = listOf(
    Office("Osijek", mockDesks),
    Office("Split", mockDesks),
    Office("Zagreb", mockDesks),
    Office("Rijeka", mockDesks),
)


