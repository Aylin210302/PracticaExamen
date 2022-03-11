package com.estados.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.estados.data.EstadoDatabase
import com.estados.model.practica
import com.estados.repository.EstadoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData : LiveData<List<practica>>
    private val repositorio : EstadoRepository

    init{
        val estadoDao = EstadoDatabase.getDatabase(application).estadoDao()
        repositorio = EstadoRepository(estadoDao)
        getAllData = repositorio.getAllData
    }

    fun addEstado(estado : practica){
        viewModelScope.launch(Dispatchers.IO) {repositorio.addEstado(estado)}
    }

    fun updateEstado(estado : practica){
        viewModelScope.launch(Dispatchers.IO) {repositorio.updateEstado(estado)}
    }

    fun deleteEstado(estado : practica){
        viewModelScope.launch(Dispatchers.IO) {repositorio.deleteLugar(estado)}
    }
}