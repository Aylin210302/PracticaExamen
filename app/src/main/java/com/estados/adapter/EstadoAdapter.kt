package com.estados.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.estados.databinding.EstadoFilaBinding
import com.estados.model.practica
import com.estados.ui.estado.EstadoFragmentDirections

class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>(){

    private var listaEstados = emptyList<practica>()

    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding)
    : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(estado : practica) {
            itemBinding.tvEstado.text = estado.estado
            itemBinding.tvCapital.text = estado.capital
            itemBinding.tvPoblacion.text = estado.poblacion.toString()
            itemBinding.tvCostas.text = estado.costas


            itemBinding.vistaFila.setOnClickListener {
                val accion = EstadoFragmentDirections
                    .actionNavEstadosToNavUpdateEstado(estado)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val itemBinding = EstadoFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return EstadoViewHolder(itemBinding) //devuelvo un card para 'pintar' un lugar
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val lugar = listaEstados[position] //recupero el luegar a 'pintar'
        holder.bind(lugar)
    }

    override fun getItemCount(): Int {
        return listaEstados.size //para conocer la cantidad de "card" que debe hacer...
    }

    fun setData(estados : List<practica>){
        this.listaEstados = estados
        notifyDataSetChanged()
    }
}