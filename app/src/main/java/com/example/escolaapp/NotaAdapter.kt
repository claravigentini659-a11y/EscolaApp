package com.example.escolaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(
    private val lista: List<Nota>
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    class NotaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val aluno: TextView = itemView.findViewById(R.id.txtAlunoNota)
        val disciplina: TextView = itemView.findViewById(R.id.txtDisciplinaNota)
        val bimestre: TextView = itemView.findViewById(R.id.txtBimestre)
        val valor: TextView = itemView.findViewById(R.id.txtValor)
        val mencao: TextView = itemView.findViewById(R.id.txtMencao)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)

        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotaViewHolder,
        position: Int
    ) {
        val nota = lista[position]

        holder.aluno.text = "Aluno: ${nota.alunoNome}"
        holder.disciplina.text = "Disciplina: ${nota.disciplinaNome}"
        holder.bimestre.text = "Bimestre: ${nota.bimestre}"
        holder.valor.text = "Valor: ${nota.valor}"
        holder.mencao.text = "Menção: ${nota.mencao}"
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}