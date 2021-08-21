package br.com.mkgcriacoes.cartaodevisita.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mkgcriacoes.cartaodevisita.R
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita

class CartaoAdapter: RecyclerView.Adapter<CartaoAdapter.CartaoViewHolder>() {
    private val cartoes = mutableListOf<CartaoVisita>()

    var onClick = { cartao: CartaoVisita -> Unit }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cartao_visita, parent, false)
        return CartaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartaoViewHolder, pos: Int) {
        val cartao = cartoes[pos]
        holder.bind(cartao)
        holder.itemView.setOnClickListener { view ->
            onClick(cartao)
        }
    }

    override fun getItemCount(): Int = cartoes.size

    fun updateCartoes(cartoes: List<CartaoVisita>){
        this.cartoes.clear()
        this.cartoes.addAll(cartoes)

        notifyDataSetChanged()
    }

    class CartaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txt_nome = itemView.findViewById<TextView>(R.id.txt_nome)
        private val txt_email = itemView.findViewById<TextView>(R.id.txt_email)
        private val txt_fone = itemView.findViewById<TextView>(R.id.txt_fone)
        private val txt_empresa = itemView.findViewById<TextView>(R.id.txt_empresa)

        fun bind(cartao: CartaoVisita){
            txt_nome.text = cartao.nome
            txt_email.text = cartao.email
            txt_fone.text = cartao.telefone
            txt_empresa.text = cartao.empresa
        }
    }
}