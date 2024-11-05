package com.example.fitnesstracker.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R
import java.text.SimpleDateFormat
import java.util.Locale

class CalculoAdapter(private val calculos: List<Calculo>) : RecyclerView.Adapter<CalculoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTipo : TextView = itemView.findViewById(R.id.tvTipo)
        val tvResultado : TextView = itemView.findViewById(R.id.tvResultado)
        val tvData : TextView = itemView.findViewById(R.id.tvData)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historico, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return calculos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calculo = calculos[position]
        holder.tvTipo.text = calculo.tipo
        holder.tvResultado.text = calculo.resultado
        holder.tvData.text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(calculo.data)
    }
}