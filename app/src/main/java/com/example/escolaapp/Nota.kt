package com.example.escolaapp

data class Nota(
    val alunoId: Int,
    val alunoNome: String,
    val disciplinaNome: String,
    val bimestre: String,
    val valor: Double,
    val mencao: String
)