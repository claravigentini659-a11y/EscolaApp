package com.example.escolaapp

import okhttp3.*
import java.io.IOException

class ApiService {

    private val client = OkHttpClient()

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
                    if (it.isSuccessful) {
                        callback(it.body?.string())
                    } else {
                        callback(null)
                    }
                }
            }
        })
    }

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
                    if (it.isSuccessful) {
                        callback(it.body?.string())
                    } else {
                        callback(null)
                    }
                }
            }
        })
    }
}
