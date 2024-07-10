package com.example.certificacion_claudio_muoz_conciertos.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Entidad local para la persistencia de datos
 */

@Entity(tableName = "local_list")

data class LocalListConcert(
    @PrimaryKey
    val id: Int,
    val artista: String,
    val fecha: String,
    val lugar: String,
    val ciudad: String,
    val imagen: String,
    val entradas: String
)