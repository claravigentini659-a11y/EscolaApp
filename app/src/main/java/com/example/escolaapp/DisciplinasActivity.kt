
package com.example.escolaapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray

class DisciplinasActivity : AppCompatActivity() {

    private lateinit var recyclerDisciplinas: RecyclerView
    private val api = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplinas)

        recyclerDisciplinas = findViewById(R.id.recyclerDisciplinas)

        recyclerDisciplinas.layoutManager =
            LinearLayoutManager(this)

        buscarDisciplinas()
    }

    private fun buscarDisciplinas() {

        api.buscarDisciplinas { resposta ->

            runOnUiThread {

                if (resposta != null) {

                    try {
                        val jsonArray = JSONArray(resposta)
                        val lista = mutableListOf<Disciplina>()

                        for (i in 0 until jsonArray.length()) {

                            val objeto = jsonArray.getJSONObject(i)

                            val disciplina = Disciplina(
                                nome = objeto.getString("nome"),
                                professor = objeto.getString("professor"),
                                cargaHoraria = objeto.getInt("cargaHoraria").toString()
                            )

                            lista.add(disciplina)
                        }

                        recyclerDisciplinas.adapter =
                            DisciplinaAdapter(lista)

                    } catch (e: Exception) {
                        Toast.makeText(
                            this,
                            "Erro ao ler as disciplinas",
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
