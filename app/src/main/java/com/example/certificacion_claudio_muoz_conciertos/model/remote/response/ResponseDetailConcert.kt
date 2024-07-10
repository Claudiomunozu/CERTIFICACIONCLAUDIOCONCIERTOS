package com.example.certificacion_claudio_muoz_conciertos.model.remote.response

data class ResponseDetailConcert(

    val id: Int,
    val artista: String,
    val fecha: String,
    val lugar: String,
    val ciudad: String,
    val imagen: String,
    val entradas: String
)