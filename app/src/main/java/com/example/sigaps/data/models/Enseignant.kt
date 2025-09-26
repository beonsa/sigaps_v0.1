package com.example.sigaps.data.models

data class Enseignant(
    val id: String,
    val nom: String,
    val prenom: String,
    val dateNaissance: String,
    val genre: String,
    val matiere: String,
    val ecoleId: String
)