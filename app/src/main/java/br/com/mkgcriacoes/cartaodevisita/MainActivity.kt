package br.com.mkgcriacoes.cartaodevisita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mkgcriacoes.cartaodevisita.adapters.CartaoAdapter
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityMainBinding
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita
import br.com.mkgcriacoes.cartaodevisita.model.Empresa
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModel
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private val binder by lazy {
      ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).cartaoVisitaRepository)
    }

    private val adapter = CartaoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        binder.rvCartoes.adapter = adapter
        binder.rvCartoes.layoutManager = LinearLayoutManager(this)

        binder.btAddCartao.setOnClickListener {
            val intent = Intent(this, NovoCartaoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAll().observe(this, { cartoes ->
            adapter.updateCartoes(cartoes)
        })
    }
}