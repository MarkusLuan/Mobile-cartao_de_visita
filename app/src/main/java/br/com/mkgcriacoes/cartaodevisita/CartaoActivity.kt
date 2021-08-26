package br.com.mkgcriacoes.cartaodevisita

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityCartaoBinding
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModel
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModelFactory
import br.com.mkgcriacoes.cartaodevisita.utils.Imagem

class CartaoActivity : AppCompatActivity() {
    private var cartao: CartaoVisita? = null

    private val binder by lazy {
        ActivityCartaoBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).cartaoVisitaRepository)
    }

    private val viewCartao by lazy {
        val view = View.inflate(this, R.layout.item_cartao_visita, null)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        val idCartao = intent.getLongExtra("cartao", -1)
        viewModel.getById(idCartao).observe(this, {cartao ->
            this.cartao = cartao

            if (cartao != null){
                renderViewCartao()
                binder.root.addView(viewCartao, 0)
            }else finish()
        })

        binder.btToggleMenu.setOnClickListener {
            if (binder.viewMenu.visibility == ConstraintLayout.VISIBLE)
                binder.viewMenu.visibility = ConstraintLayout.GONE
            else binder.viewMenu.visibility = ConstraintLayout.VISIBLE
        }

        binder.btEnviar.setOnClickListener {
            Imagem.compartilhar(this, viewCartao)
        }

        binder.btEditar.setOnClickListener {
            val intent = Intent(this, NovoCartaoActivity::class.java).apply {
                putExtra("editar", cartao?.id)
            }

            startActivity(intent)
        }

        binder.btDeletar.setOnClickListener {
            viewModel.delete(cartao!!)
        }
    }

    fun renderViewCartao(){
        viewCartao.findViewById<TextView>(R.id.txt_nome).text = cartao?.nome
        viewCartao.findViewById<TextView>(R.id.txt_email).text = cartao?.email
        viewCartao.findViewById<TextView>(R.id.txt_fone).text = cartao?.telefone
        viewCartao.findViewById<TextView>(R.id.txt_empresa).text = cartao?.empresa

        val cor = ColorStateList.valueOf(Color.parseColor(cartao?.cor))
        ViewCompat.setBackgroundTintList(viewCartao.findViewById(R.id.view_cor), cor)
    }
}