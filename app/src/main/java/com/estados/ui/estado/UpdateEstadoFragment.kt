package com.estados.ui.estado

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.estados.R
import com.estados.databinding.FragmentUpdateEstadoBinding
import com.estados.databinding.FragmentEstadoBinding
import com.estados.model.practica
import com.estados.viewmodel.EstadoViewModel

class UpdateEstadoFragment : Fragment() {
    private lateinit var estadoViewModel: EstadoViewModel
    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateEstadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel = ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        //obtengo la info del estado y la coloco en el fragmento
        binding.etEstado.setText(args.estado.estado)
        binding.etCapital.setText(args.estado.capital)
        binding.etPoblacion.setText(args.estado.poblacion.toString())
        binding.etCostas.setText(args.estado.costas)

        binding.btUpdateEstado.setOnClickListener { actualizarEstado() }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es delete ...
        if(item.itemId==R.id.delete_menu) {
            deleteEstado()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actualizarEstado() {
        val estado = binding.etEstado.text.toString()
        if(estado.isNotEmpty()) {
            val capital = binding.etCapital.text.toString()
            val poblacion = binding.etPoblacion.text.toString().toInt()
            val costas = binding.etCostas.text.toString()

            val estado = practica(args.estado.id, estado, capital,poblacion,costas)
            estadoViewModel.updateEstado(estado)
            Toast.makeText(requireContext(),
                getString(R.string.msg_estados_add),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_nav_update_estado_to_nav_estados)
        }    }

    private fun deleteEstado(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_seguroBorrar)+ " ${args.estado.capital}?")
        builder.setNegativeButton(getString(R.string.no)) {_,_ ->}
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            estadoViewModel.deleteEstado(args.estado)
            findNavController().navigate(R.id.action_nav_update_estado_to_nav_estados)
        }
        builder.create().show()
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}