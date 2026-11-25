
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

class NotasActivity : AppCompatActivity() {

    private lateinit var campoId: EditText
    private lateinit var txtNomeAluno: TextView
    private lateinit var recyclerNotas: RecyclerView

    private val api = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        campoId = findViewById(R.id.etIdAlunoNotas)
        txtNomeAluno = findViewById(R.id.txtNomeAlunoNotas)
        recyclerNotas = findViewById(R.id.recyclerNotas)

        recyclerNotas.layoutManager = LinearLayoutManager(this)
        recyclerNotas.adapter = NotaAdapter(emptyList())

        findViewById<Button>(R.id.btnBuscarNotas).setOnClickListener {

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

            buscarNotas(alunoId)
        }
    }

    private fun buscarNotas(alunoId: Int) {

        txtNomeAluno.text = ""
        recyclerNotas.adapter = NotaAdapter(emptyList())

        api.buscarNotas(alunoId) { statusCode, resposta ->

            runOnUiThread {

                when (statusCode) {

                    200 -> {
                        try {
                            val jsonArray = JSONArray(resposta ?: "[]")
                            val lista = mutableListOf<Nota>()

                            for (i in 0 until jsonArray.length()) {

                                val objeto = jsonArray.getJSONObject(i)

                                lista.add(
                                    Nota(
                                        alunoId = objeto.getInt("alunoId"),
                                        alunoNome = objeto.getString("alunoNome"),
                                        disciplinaNome = objeto.getString("disciplinaNome"),
                                        bimestre = objeto.getString("bimestre"),
                                        valor = objeto.getDouble("valor"),
                                        mencao = objeto.getString("mencao")
                                    )
                                )
                            }

                            if (lista.isEmpty()) {
                                txtNomeAluno.text = "Nenhuma nota encontrada"
                            } else {
                                txtNomeAluno.text = lista.first().alunoNome
                            }

                            recyclerNotas.adapter = NotaAdapter(lista)

                        } catch (e: Exception) {
                            Toast.makeText(
                                this,
                                "Erro ao ler as notas",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    404 -> {
                        txtNomeAluno.text = ""
                        recyclerNotas.adapter = NotaAdapter(emptyList())

                        Toast.makeText(
                            this,
                            "Aluno não encontrado",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        Toast.makeText(
                            this,
                            "Erro ao buscar notas",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}
