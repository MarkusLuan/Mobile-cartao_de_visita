package br.com.mkgcriacoes.cartaodevisita

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binder.root)

        binder.edTxtTelefone.editText?.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        binder.btSalvarCartao.setOnClickListener {
            val cartaoVisita = CartaoVisita(
                nome = binder.edTxtNome.editText?.text.toString(),
                empresa = binder.edTxtEmpresa.editText?.text.toString(),
                telefone = binder.edTxtTelefone.editText?.text.toString(),
                email = binder.edTxtEmail.editText?.text.toString(),
                cor = binder.btCor.background.toString()
            )

            viewModel.insert(cartaoVisita)
            finish()
        }
    }
}