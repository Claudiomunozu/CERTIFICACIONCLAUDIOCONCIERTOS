package com.example.certificacion_claudio_muoz_conciertos.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.certificacion_claudio_muoz_conciertos.R
import com.example.certificacion_claudio_muoz_conciertos.databinding.FragmentFirstBinding
import com.example.certificacion_claudio_muoz_conciertos.view.adapter.AdapterConcert
import com.example.certificacion_claudio_muoz_conciertos.viewmodel.ViewModelConcert

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ViewModelConcert by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterConcert()

        binding.vgRecyclerView.adapter = adapter
        binding.vgRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.getList().observe(viewLifecycleOwner){
            it.let {
                Log.d("listado", it.toString())
                adapter.setData(it)
            }
        }
        /*
         * Observa selected item
         */
        adapter.selected().observe(viewLifecycleOwner){
            it.let {
                Log.d("selection", it.toString())
                viewModel.getDetailsByIdFromNetwork(it.id)
            }
            /*
             * Envio de datos con bundle
             */
            val bunlde = Bundle().apply {
                putInt("ID_KEY", it.id)
                putString("NAME_KEY", it.artista)
                putString("URL_KEY", it.entradas)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bunlde)
        }
    }
}