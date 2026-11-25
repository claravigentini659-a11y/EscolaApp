
package com.example.escolaapp

import okhttp3.*
import java.io.IOException

class ApiService {

    private val client = OkHttpClient()

    // BUSCAR DISCIPLINAS
    fun buscarDisciplinas(callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url("https://provapam.runasp.net/api/disciplinas")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    callback(if (it.isSuccessful) it.body?.string() else null)
                }
            }
        })
    }

    // BUSCAR ALUNOS
    fun buscarAlunos(callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url("https://provapam.runasp.net/api/alunos")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    callback(if (it.isSuccessful) it.body?.string() else null)
                }
            }
        })
    }

    // BUSCAR NOTAS
    fun buscarNotas(alunoId: Int, callback: (Int, String?) -> Unit) {
        val request = Request.Builder()
            .url("https://provapam.runasp.net/api/notas/aluno/$alunoId")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(0, null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    callback(it.code, it.body?.string())
                }
            }
        })
    }

    // BUSCAR MATRÍCULAS
    fun buscarMatriculas(alunoId: Int, callback: (Int, String?) -> Unit) {
        val request = Request.Builder()
            .url("https://provapam.runasp.net/api/matriculas/aluno/$alunoId")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(0, null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    callback(it.code, it.body?.string())
                }
            }
        })
    }
}
