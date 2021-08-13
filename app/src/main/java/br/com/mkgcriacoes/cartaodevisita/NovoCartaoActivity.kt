package br.com.mkgcriacoes.cartaodevisita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityNovoCartaoBinding

class NovoCartaoActivity : AppCompatActivity() {
    private val binder by lazy {
        ActivityNovoCartaoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        binder.btSalvarCartao.setOnClickListener {
            finish()
        }
    }
}