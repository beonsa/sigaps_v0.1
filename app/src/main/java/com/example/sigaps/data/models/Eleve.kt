package com.example.sigaps.data.models

data class Eleve(
    val id: String,
    val nom: String,
    val prenom: String,
    val dateNaissance: String,
    val genre: String,
    val classe: String,
    val ecoleId: String
)