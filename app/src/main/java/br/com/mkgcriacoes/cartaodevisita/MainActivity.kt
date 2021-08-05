package br.com.mkgcriacoes.cartaodevisita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binder by lazy {
      ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
    }
}