
package com.example.escolaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisciplinaAdapter(
    private val lista: List<Disciplina>
) : RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder>() {

    class DisciplinaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val nome: TextView = itemView.findViewById(R.id.txtNome)
        val professor: TextView = itemView.findViewById(R.id.txtProfessor)
        val cargaHoraria: TextView = itemView.findViewById(R.id.txtCargaHoraria)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisciplinaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_disciplina, parent, false)

        return DisciplinaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DisciplinaViewHolder,
        position: Int
    ) {
        val disciplina = lista[position]

        holder.nome.text = disciplina.nome
        holder.professor.text = "Professor: ${disciplina.professor}"
        holder.cargaHoraria.text = "Carga horária: ${disciplina.cargaHoraria} horas"
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
