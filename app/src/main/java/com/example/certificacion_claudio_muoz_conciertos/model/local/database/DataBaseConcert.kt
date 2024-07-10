package com.example.certificacion_claudio_muoz_conciertos.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.dao.DaoConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalDetailConcert

@Database(
    entities = [LocalListConcert::class, LocalDetailConcert::class],
    version = 1,
    exportSchema = false
)

/*
 * Clase abstracta que hereda de RoomDatabase
 */

abstract class DataBaseConcert : RoomDatabase() {

    abstract fun getActionDao(): DaoConcert

    companion object {
        @Volatile
        private var INSTANCE: DataBaseConcert? = null

        /*
         * Método para obtener la instancia de la base de datos
         */

        fun getDataBase(context: Context): DataBaseConcert {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseConcert::class.java,
                    "DataBaseF"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
