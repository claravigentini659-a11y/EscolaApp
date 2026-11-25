
package com.example.escolaapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class MatriculasActivity : AppCompatActivity() {

    private lateinit var campoId: EditText
    private lateinit var txtNomeAluno: TextView
    private lateinit var recyclerMatriculas: RecyclerView

    private val api = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matriculas)

        campoId = findViewById(R.id.etIdAlunoMatriculas)
        txtNomeAluno = findViewById(R.id.txtNomeAlunoMatriculas)
        recyclerMatriculas = findViewById(R.id.recyclerMatriculas)

        recyclerMatriculas.layoutManager = LinearLayoutManager(this)
        recyclerMatriculas.adapter = MatriculaAdapter(emptyList())

        findViewById<Button>(R.id.btnBuscarMatriculas).setOnClickListener {

            val textoId = campoId.text.toString().trim()
            val alunoId = textoId.toIntOrNull()

            if (textoId.isEmpty() || alunoId == null) {
                Toast.makeText(
                    this,
                    "Digite um ID válido",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            buscarMatriculas(alunoId)
        }
    }

    private fun buscarMatriculas(alunoId: Int) {

        txtNomeAluno.text = ""
        recyclerMatriculas.adapter = MatriculaAdapter(emptyList())

        api.buscarMatriculas(alunoId) { statusCode, resposta ->

            runOnUiThread {

                when (statusCode) {

                    200 -> {
                        try {
                            val jsonArray = JSONArray(resposta ?: "[]")
                            val lista = mutableListOf<Matricula>()

                            for (i in 0 until jsonArray.length()) {

                                val objeto = jsonArray.getJSONObject(i)

                                lista.add(
                                    Matricula(
                                        alunoId = objeto.getInt("alunoId"),
                                        alunoNome = objeto.getString("alunoNome"),
                                        disciplinaNome = objeto.getString("disciplinaNome"),
                                        dataMatricula = objeto.getString("dataMatricula"),
                                        situacao = objeto.getString("situacao")
                                    )
                                )
                            }

                            if (lista.isEmpty()) {
                                txtNomeAluno.text = "Nenhuma matrícula encontrada"
                            } else {
                                txtNomeAluno.text = lista.first().alunoNome
                            }

                            recyclerMatriculas.adapter = MatriculaAdapter(lista)

                        } catch (e: Exception) {
                            Toast.makeText(
                                this,
                                "Erro ao ler as matrículas",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    404 -> {
                        txtNomeAluno.text = ""
                        recyclerMatriculas.adapter = MatriculaAdapter(emptyList())

                        Toast.makeText(
                            this,
                            "Aluno não encontrado",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        Toast.makeText(
                            this,
                            "Erro ao buscar matrículas",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}
