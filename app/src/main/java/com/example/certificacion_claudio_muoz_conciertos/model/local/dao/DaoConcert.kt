package com.example.certificacion_claudio_muoz_conciertos.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalDetailConcert

/*
 * Dao para las entidades
 */

@Dao
interface DaoConcert {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<LocalListConcert>)

    @Query("SELECT * FROM local_list ORDER BY id ASC")
    fun getAll(): LiveData<List<LocalListConcert>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(details: LocalDetailConcert)

    @Query("SELECT * FROM local_detail WHERE id = :id")
    fun getDetailById(id: Int): LiveData<LocalDetailConcert>
}