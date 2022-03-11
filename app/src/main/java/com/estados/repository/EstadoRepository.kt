package com.estados.repository

import androidx.lifecycle.LiveData
import com.estados.data.EstadoDao
import com.estados.model.practica

class EstadoRepository(private val estadoDao: EstadoDao) {
    val getAllData : LiveData<List<practica>> = estadoDao.getAllData()

    suspend fun addEstado(estado : practica){
        estadoDao.addEstado(estado)
    }

    suspend fun updateEstado(estado : practica){
        estadoDao.updateEstado(estado)
    }

    suspend fun deleteLugar(estado : practica){
        estadoDao.deleteEstado(estado)
    }
}