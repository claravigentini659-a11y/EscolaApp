package com.example.escolaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlunoAdapter(
    private val lista: List<Aluno>
) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    class AlunoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.txtId)
        val nome: TextView = itemView.findViewById(R.id.txtNomeAluno)
        val rm: TextView = itemView.findViewById(R.id.txtRm)
        val turma: TextView = itemView.findViewById(R.id.txtTurma)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlunoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AlunoViewHolder,
        position: Int
    ) {
        val aluno = lista[position]
        holder.id.text = "ID: ${aluno.id}"
        holder.nome.text = aluno.nome
        holder.rm.text = "RM: ${aluno.rm}"
        holder.turma.text = "Turma: ${aluno.turma}"
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}