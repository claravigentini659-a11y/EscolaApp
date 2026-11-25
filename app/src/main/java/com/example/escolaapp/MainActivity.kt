package com.example.escolaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnDisciplinas).setOnClickListener {
            val tela = Intent(this, DisciplinasActivity::class.java)
            startActivity(tela)
        }
        findViewById<Button>(R.id.btnAlunos).setOnClickListener {
            val tela = Intent(this, AlunosActivity::class.java)
            startActivity(tela)
        }
    }
}