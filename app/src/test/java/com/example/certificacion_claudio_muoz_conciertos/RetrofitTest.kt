package com.example.certificacion_claudio_muoz_conciertos

import com.example.certificacion_claudio_muoz_conciertos.model.remote.retrofit.RetrofitConcert
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {


    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
    }

    @After
    fun tearDown() {

        mockWebServer.shutdown()

    }

    /*
    * Test Retrofit verifica que la URL base de la instancia de Retrofit
    *  sea igual a la expectedBaseUrl utilizando Assert.assertEquals.
    */
    @Test
    fun testRetrofit() {

        val expectedBaseUrl = mockWebServer.url("/").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RetrofitConcert.retrofitTest = retrofit
        val retrofitInstace = RetrofitConcert.retrofitTest
        Assert.assertEquals(expectedBaseUrl, retrofitInstace.baseUrl().toString())
    }
}