package br.com.mkgcriacoes.cartaodevisita.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

abstract class Imagem {
    companion object {
        private fun viewToImg(view: View): Bitmap? {
            var img: Bitmap? = null
            try {
                img = Bitmap.createBitmap(
                    view.measuredWidth,
                    view.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )

                val c = Canvas(img)
                view.draw(c)
            } catch (e: Exception) {
                Log.e("Erro ao capturar cartão", e.message.toString())
            }

            return img
        }

        private fun imgToUri(context: Context, img: Bitmap): Uri? {
            val arquivo = "${System.currentTimeMillis()}.png"

            // Salvar na cache em vez de em pictures
            val imgFile = File(context.externalCacheDir, arquivo)
            imgFile.deleteOnExit()

            FileOutputStream(imgFile).use {
                img.compress(Bitmap.CompressFormat.PNG, 100, it)
            }

            return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", imgFile)
        }

        fun compartilhar(context: Context, view: View) {
            val img = viewToImg(view)
            val uri = imgToUri(context, img!!)

            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/png"
            }

            val chooser = Intent.createChooser(intent, "Compartilhar Cartão Via")

            val resInfoList: List<ResolveInfo> = context.packageManager
                .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)

            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                context.grantUriPermission(
                    packageName,
                    uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

            context.startActivity(chooser)
        }
    }
}