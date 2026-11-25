package com.example.escolaapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class AlunosActivity : AppCompatActivity() {

    private lateinit var recyclerAlunos: RecyclerView
    private val api = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alunos)

        recyclerAlunos = findViewById(R.id.recyclerAlunos)
        recyclerAlunos.layoutManager = LinearLayoutManager(this)

        buscarAlunos()
    }

    private fun buscarAlunos() {
        api.buscarAlunos { resposta ->
            runOnUiThread {
                if (resposta != null) {
                    try {
                        val jsonArray = JSONArray(resposta)
                        val lista = mutableListOf<Aluno>()

                        for (i in 0 until jsonArray.length()) {
                            val objeto = jsonArray.getJSONObject(i)

                            val aluno = Aluno(
                                id = objeto.getInt("id"),
                                nome = objeto.getString("nome"),
                                rm = objeto.getString("rm"),
                                turma = objeto.getString("turma")
                            )

                            lista.add(aluno)
                        }

                        recyclerAlunos.adapter = AlunoAdapter(lista)

                    } catch (e: Exception) {
                        Toast.makeText(
                            this,
                            "Erro ao ler os alunos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Erro ao conectar com a API",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}