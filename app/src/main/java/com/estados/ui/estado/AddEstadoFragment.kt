package com.estados.ui.estado

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.estados.R
import com.estados.databinding.FragmentAddEstadoBinding
import com.estados.databinding.FragmentEstadoBinding
import com.estados.model.practica
import com.estados.viewmodel.EstadoViewModel

class AddEstadoFragment : Fragment() {
    private lateinit var estadoViewModel: EstadoViewModel
    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)

        binding.btAddEstado.setOnClickListener { agregarEstado() }

        return binding.root
    }

    private fun agregarEstado() {
        val estado = binding.etEstado.text.toString()
        if(estado.isNotEmpty()) {
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toInt()
            val costas = binding.etCostas.text.toString()

            val estado = practica(0, estado, capital,poblacion,costas)
            estadoViewModel.addEstado(estado)
            Toast.makeText(requireContext(),
                getString(R.string.msg_estados_add),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addEstadoFragment_to_nav_estados)
        }    }


}