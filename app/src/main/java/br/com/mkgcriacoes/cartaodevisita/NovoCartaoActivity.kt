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

    private var cor = "#000000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

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
            val cartaoVisita = CartaoVisita(
                nome = binder.edTxtNome.editText?.text.toString(),
                empresa = binder.edTxtEmpresa.editText?.text.toString(),
                telefone = binder.edTxtTelefone.editText?.text.toString(),
                email = binder.edTxtEmail.editText?.text.toString(),
                cor = this.cor
            )

            viewModel.insert(cartaoVisita)
            finish()
        }
    }
}