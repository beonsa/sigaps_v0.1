package com.example.sigaps.data.models

data class Ecole(
    val id: String,
    val nom: String,
    val adresse: String,
    val ville: String,
    val telephone: String,
    val email: String,
    val latitude: Double,
    val longitude: Double
)