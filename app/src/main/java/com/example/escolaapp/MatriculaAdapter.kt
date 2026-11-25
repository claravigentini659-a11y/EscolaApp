
package com.example.escolaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatriculaAdapter(
    private val lista: List<Matricula>
) : RecyclerView.Adapter<MatriculaAdapter.MatriculaViewHolder>() {

    class MatriculaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val aluno: TextView = itemView.findViewById(R.id.txtAlunoMatricula)
        val disciplina: TextView = itemView.findViewById(R.id.txtDisciplinaMatricula)
        val data: TextView = itemView.findViewById(R.id.txtDataMatricula)
        val situacao: TextView = itemView.findViewById(R.id.txtSituacao)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatriculaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_matricula, parent, false)

        return MatriculaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MatriculaViewHolder,
        position: Int
    ) {
        val matricula = lista[position]

        holder.aluno.text = "Aluno: ${matricula.alunoNome}"
        holder.disciplina.text = "Disciplina: ${matricula.disciplinaNome}"
        holder.data.text = "Data: ${matricula.dataMatricula}"
        holder.situacao.text = "Situação: ${matricula.situacao}"
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
