package com.example.certificacion_claudio_muoz_conciertos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.certificacion_claudio_muoz_conciertos.model.local.database.DataBaseConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalDetailConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert
import com.example.certificacion_claudio_muoz_conciertos.model.repository.RepositoryConcert
import kotlinx.coroutines.launch

class ViewModelConcert(application: Application) : AndroidViewModel(application) {

    private val repository: RepositoryConcert
    private var idSelected: Int = 0

    /*
    * Inicializa el repositorio
     */
    init {
        val db = DataBaseConcert.getDataBase(application)
        val daoVm = db.getActionDao()
        repository = RepositoryConcert(daoVm)

        viewModelScope.launch {
            repository.fetchList()
        }
    }

    /*
    * Devuelve la lista de conciertos
     */
    fun getList(): LiveData<List<LocalListConcert>> = repository.listLiveData

    /*
    * Devuelve los detalles del concierto
     */

    fun getDetailsByIdFromNetwork(id: Int) = viewModelScope.launch {
        idSelected = id
        repository.fetchDetails(idSelected)
    }

    /*
    * Devuelve los detalles del concierto
     */
    fun getDetail(): LiveData<LocalDetailConcert> = repository.getDetailsById(idSelected)
}