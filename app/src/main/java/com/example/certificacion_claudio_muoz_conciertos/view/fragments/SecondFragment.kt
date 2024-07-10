package com.example.certificacion_claudio_muoz_conciertos.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.certificacion_claudio_muoz_conciertos.databinding.FragmentSecondBinding
import com.example.certificacion_claudio_muoz_conciertos.viewmodel.ViewModelConcert

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModelConcert by activityViewModels()
    private var id: Int = 0
    private var name : String = ""
    private var url: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
         * Recibo el bundle de la lista y traigo detalles del concierto
         */

        arguments?.let { bundle ->
            id = bundle.getInt("ID_KEY")
            name = bundle.getString("NAME_KEY").toString()
            url = bundle.getString("URL_KEY").toString()
            Log.d("Bundle", "id: $id, name: $name")
        }
        id.let { id ->
            viewModel.getDetailsByIdFromNetwork(id)
        }

        viewModel.getDetail().observe(viewLifecycleOwner) {
            if (it != null) {
                Glide.with(binding.ivDetalle).load(it.imagen).into(binding.ivDetalle)
                binding.tvDetalleArtista.text = it.artista
                binding.tvDetalleCiudad.text = it.ciudad
                binding.tvDetalleFecha.text = it.fecha
                binding.tvDetalleLugar.text = it.lugar
                binding.button.setOnClickListener {
                    comparEntradas(url)
                }
            }
        }
    }

    /*
     * Funcion para abrir la pagina web del concierto (compra entradas)
     */
    private fun comparEntradas(url: String) {
        Log.d("fun", "funcion comprar entradas")
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
       // context?.startActivity(intent)

       try {context?.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error al abrir pagina web", Toast.LENGTH_SHORT)
                .show()
        }
    }
}