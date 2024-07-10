package com.example.certificacion_claudio_muoz_conciertos.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.certificacion_claudio_muoz_conciertos.model.local.dao.DaoConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalDetailConcert
import com.example.certificacion_claudio_muoz_conciertos.model.remote.mapper.fromInternetDetails
import com.example.certificacion_claudio_muoz_conciertos.model.remote.mapper.fromInternetList
import com.example.certificacion_claudio_muoz_conciertos.model.remote.retrofit.RetrofitConcert

class RepositoryConcert(private val dao: DaoConcert) {

    private val networkService = RetrofitConcert.retrofitInstance()
    val listLiveData = dao.getAll()

    /*
     * funcion que llama al dao para obtener la lista de conciertos
     */

    suspend fun fetchList() {
        val service = kotlin.runCatching { networkService.fetchList() }

        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("LISTA CONCIERTOS", it.toString())
                    dao.insertAll(fromInternetList(it))
                }

                else -> Log.d("REPOSITORY", "${it.code()} ${it.errorBody()}")
            }
            service.onFailure {
                Log.e("ERROR", "${it.message}")
            }
        }
    }

    /*
     * funcion que llama al dao para obtener los detalles de un concierto
     */
    suspend fun fetchDetails(id: Int): LocalDetailConcert? {
        val service = kotlin.runCatching { networkService.fetchDetail(id) }

        return service.getOrNull()?.body()?.let { Details ->
            val details = fromInternetDetails(Details)
            dao.insertDetail(details)
            details
        }
    }

    /*
     * funcion que llama al dao para obtener los detalles de un concierto por id
     */
    fun getDetailsById(id: Int): LiveData<LocalDetailConcert> {
        return dao.getDetailById(id)
    }
}