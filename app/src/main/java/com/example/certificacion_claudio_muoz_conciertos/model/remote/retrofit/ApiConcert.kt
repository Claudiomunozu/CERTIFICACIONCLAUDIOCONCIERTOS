package com.example.certificacion_claudio_muoz_conciertos.model.remote.retrofit

import com.example.certificacion_claudio_muoz_conciertos.model.remote.response.ResponseDetailConcert
import com.example.certificacion_claudio_muoz_conciertos.model.remote.response.ResponseListConcert
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiConcert {

    /*
     * obtengo lista de conciertos
     */

    @GET("conciertos")
    suspend fun fetchList(): Response<List<ResponseListConcert>>

    /*
     * obtengo detalle de un concierto
     */

    @GET("conciertos/{id}")
    suspend fun fetchDetail(@Path("id") id: Int): Response<ResponseDetailConcert>
}