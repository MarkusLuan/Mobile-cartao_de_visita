package br.com.mkgcriacoes.cartaodevisita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mkgcriacoes.cartaodevisita.adapters.CartaoAdapter
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityMainBinding
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita
import br.com.mkgcriacoes.cartaodevisita.model.Empresa

class MainActivity : AppCompatActivity() {
    private val binder by lazy {
      ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter = CartaoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        binder.rvCartoes.adapter = adapter
        binder.rvCartoes.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()

        val cartoes = arrayOf<CartaoVisita>(CartaoVisita().apply {
            this.nome = "Markus Luan de Brito Sousa Pinheiro"
            this.email = "teste@mkgcriacoes.com.br"
            this.telefone = "(84) 9 1518-4208"
            this.empresa = Empresa().apply { this.razaoSocial = "Empresa de teste" }
        })

        adapter.updateCartoes(cartoes.toList())
    }

    fun criarNovoCartao(view: View) {
        val intent = Intent(this, NovoCartaoActivity::class.java)
        startActivity(intent)
    }
}