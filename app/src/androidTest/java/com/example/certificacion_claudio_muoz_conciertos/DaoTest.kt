package com.example.certificacion_claudio_muoz_conciertos

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.platform.app.InstrumentationRegistry
import com.example.certificacion_claudio_muoz_conciertos.model.local.dao.DaoConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.database.DataBaseConcert
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var db: DataBaseConcert
    private lateinit var concertDao: DaoConcert

    /*
     * Configuración inicial de la base de datos
     */

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, DataBaseConcert::class.java).build()
        concertDao = db.getActionDao()
    }

    /*
     * testeando insertar datos en la base de datos
     */
    @Test
    fun testOperations() = runBlocking {

        val expectedConcert = listOf(
            LocalListConcert(
                3, "chancho en piedra", "12/06/2024", "Movistar Arena",
                "Santiago", "img1", "www.puntoticket.com"
            ),

            LocalListConcert(
                5, "JUDAS PRIEST", "30/06/2024", "Movistar Arena",
                "Santiago", "img1", "www.puntoticket.com"
            )
        )

        // Insertar datos
        concertDao.insertAll(expectedConcert)

        // Observar LiveData
        UiThreadStatement.runOnUiThread {
            // Obtener LiveData
            val liveData = concertDao.getAll()

            // Crear el observador
            val observer = Observer<List<LocalListConcert>> { concert ->

                ViewMatchers.assertThat(concert, CoreMatchers.not(emptyList()))
                Assert.assertEquals(2, concert.size)
            }

            // Observar los conciertos ingresados
            liveData.observeForever(observer)

            // Quitar el observador
            liveData.removeObserver(observer)
        }
    }

    @After
    fun teardown() {
        db.close()
    }
}