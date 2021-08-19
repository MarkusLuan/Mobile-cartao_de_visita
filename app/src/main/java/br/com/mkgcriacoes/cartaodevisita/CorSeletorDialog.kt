package br.com.mkgcriacoes.cartaodevisita

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton


class CorSeletorDialog(
    context: Context
): AlertDialog.Builder(context) {
    private var alertDialog: AlertDialog? = null
    private var onCorSelectListner = { cor:String -> }
    private val cores = arrayListOf(
        "#FF0000",
        "#00FF00",
        "#0000FF",
        "#FFFF00",
        "#FF00FF",
        "#00FFFF",
        "#000000"
    )

    init {
        super.setTitle("Selecione uma cor")
        super.setView(prepararView())
    }

    override fun show(): AlertDialog {
        alertDialog = super.show()
        return alertDialog!!
    }

    fun setOnCorSelectListner(onCorSelectListner: (cor: String) -> Unit){
        this.onCorSelectListner = onCorSelectListner
    }

    private fun prepararView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_seletor_cor, null, false) as GridLayout

        for (cor in cores){
            val bt_cor = MaterialButton(context)
            val params = LinearLayout.LayoutParams(toDp(40f), toDp(50f))

            params.leftMargin = 10
            params.bottomMargin = 10
            bt_cor.layoutParams = params

            bt_cor.cornerRadius = 50
            bt_cor.setBackgroundColor(Color.parseColor(cor))
            bt_cor.setOnClickListener {
                onCorSelectListner(cor)
                alertDialog?.dismiss()
            }

            view.addView(bt_cor)
        }

        return view
    }

    private fun toDp(value: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics).toInt()
}