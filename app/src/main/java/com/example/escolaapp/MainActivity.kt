
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
            startActivity(Intent(this, DisciplinasActivity::class.java))
        }

        findViewById<Button>(R.id.btnAlunos).setOnClickListener {
            startActivity(Intent(this, AlunosActivity::class.java))
        }

        findViewById<Button>(R.id.btnNotas).setOnClickListener {
            startActivity(Intent(this, NotasActivity::class.java))
        }

        findViewById<Button>(R.id.btnMatriculas).setOnClickListener {
            startActivity(Intent(this, MatriculasActivity::class.java))
        }
    }
}
