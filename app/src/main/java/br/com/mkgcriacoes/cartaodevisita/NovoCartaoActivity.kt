package br.com.mkgcriacoes.cartaodevisita

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.activity.viewModels
import br.com.mkgcriacoes.cartaodevisita.databinding.ActivityNovoCartaoBinding
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModel
import br.com.mkgcriacoes.cartaodevisita.model.MainViewModelFactory

class NovoCartaoActivity : AppCompatActivity() {
    private val binder by lazy {
        ActivityNovoCartaoBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).cartaoVisitaRepository)
    }

    private var cartao: CartaoVisita? = null
    private var cor = "#000000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        val idCartao = intent.getLongExtra("editar", -1)
        if (idCartao > -1) {
            viewModel.getById(idCartao).observe(this, { cartao ->
                this.cartao = cartao

                if (cartao != null) {
                    cor = cartao.cor

                    binder.edTxtNome.editText?.setText(cartao.nome)
                    binder.edTxtEmpresa.editText?.setText(cartao.empresa)
                    binder.edTxtTelefone.editText?.setText(cartao.telefone)
                    binder.edTxtEmail.editText?.setText(cartao.email)
                    binder.btCor.setBackgroundColor(Color.parseColor(cor))
                }
            })
        }

        binder.edTxtTelefone.editText?.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binder.btCor.setOnClickListener {
            val corDialog = CorSeletorDialog(this)
            corDialog.setOnCorSelectListner {cor ->
                this.cor = cor
                binder.btCor.setBackgroundColor(Color.parseColor(cor))
            }
            corDialog.show()
        }

        binder.btSalvarCartao.setOnClickListener {
            if (cartao == null) {
                cartao = CartaoVisita(
                    nome = binder.edTxtNome.editText?.text.toString(),
                    empresa = binder.edTxtEmpresa.editText?.text.toString(),
                    telefone = binder.edTxtTelefone.editText?.text.toString(),
                    email = binder.edTxtEmail.editText?.text.toString(),
                    cor = this.cor
                )
            }

            viewModel.insert(cartao!!)
            finish()
        }
    }
}