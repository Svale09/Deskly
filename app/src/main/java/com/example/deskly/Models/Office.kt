package com.example.deskly.Models

data class Office(
    val id: String,
    val name: String,
    val location: String
)

var mockOffices: List<Office> = listOf(
    Office("OS", "Osijek", "Osijek"),
    Office(id = "SP", name = "Split", location = "Split"),
    Office(id = "ZG", name = "Zagreb", location = "Beograd"),
    Office(id = "RI", name = "Rijeka", location = "Novi Sad"),
)


