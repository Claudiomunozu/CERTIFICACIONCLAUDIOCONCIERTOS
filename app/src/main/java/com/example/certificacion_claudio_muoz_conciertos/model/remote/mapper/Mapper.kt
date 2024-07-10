package com.example.certificacion_claudio_muoz_conciertos.model.remote.mapper

import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalDetailConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert
import com.example.certificacion_claudio_muoz_conciertos.model.remote.response.ResponseDetailConcert
import com.example.certificacion_claudio_muoz_conciertos.model.remote.response.ResponseListConcert

/*
Archivo Mapper, Mapea la info obtenida desde la API a las entidades de la base de datos
 */

fun fromInternetList(list: List<ResponseListConcert>): List<LocalListConcert> {

    return list.map {
        LocalListConcert(
            id = it.id,
            artista = it.artista,
            fecha = it.fecha,
            lugar = it.lugar,
            ciudad = it.ciudad,
            imagen = it.imagen,
            entradas = it.entradas
        )
    }
}


fun fromInternetDetails(details: ResponseDetailConcert): LocalDetailConcert {

    return LocalDetailConcert(

        id = details.id,
        artista = details.artista,
        fecha = details.fecha,
        lugar = details.lugar,
        ciudad = details.ciudad,
        imagen = details.imagen,
        entradas = details.entradas,
    )
}