package com.example.certificacion_claudio_muoz_conciertos.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.certificacion_claudio_muoz_conciertos.databinding.ItemConcertBinding
import com.example.certificacion_claudio_muoz_conciertos.model.local.entities.LocalListConcert

class AdapterConcert : RecyclerView.Adapter<AdapterConcert.ViewHolder>() {

    private var list = listOf<LocalListConcert>()
    private val selected = MutableLiveData<LocalListConcert>()


    fun selected(): LiveData<LocalListConcert> = selected

    /*
     * Método setData
     */

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<LocalListConcert>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemConcertBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AdapterConcert.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /*
     * Clase interna ViewHolder
     */

    inner class ViewHolder(private val binding: ItemConcertBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(list: LocalListConcert) {


            Glide.with(binding.ivArtista).load(list.imagen).centerCrop().into(binding.ivArtista)
            binding.tvNombreArtista.text = list.artista
            binding.tvFecha.text = list.fecha
            binding.tVLugar.text = list.lugar
            binding.tvCiudad.text = list.ciudad

            itemView.setOnClickListener(this)
        }

        /*
         * funcion onClick
         */
        override fun onClick(v: View?) {
            selected.value = list[bindingAdapterPosition]
            Log.d("ON CLICK POSITION", bindingAdapterPosition.toString())
        }
    }
}

