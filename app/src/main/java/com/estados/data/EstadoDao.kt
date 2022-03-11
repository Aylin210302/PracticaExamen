package com.estados.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.estados.model.practica

@Dao

interface EstadoDao {
    @Query("select * from ESTADOS")
    fun getAllData() : LiveData<List<practica>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(estado: practica)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(estado: practica)

    @Delete
    suspend fun deleteEstado(estado: practica)
}