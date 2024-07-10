package com.example.certificacion_claudio_muoz_conciertos.model.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConcert {

    companion object {

        private const val BASE_URL = "https://jp-conciertos.onrender.com/"

        lateinit var retrofitTest : Retrofit

        fun retrofitInstance(): ApiConcert {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(ApiConcert::class.java)
        }
    }
}