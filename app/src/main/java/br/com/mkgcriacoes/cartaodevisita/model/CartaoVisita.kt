package br.com.mkgcriacoes.cartaodevisita.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartaoVisita (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val nome: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val cor: String
)