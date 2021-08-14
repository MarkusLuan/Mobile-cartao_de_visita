package br.com.mkgcriacoes.cartaodevisita

import android.app.Application
import br.com.mkgcriacoes.cartaodevisita.repository.AppDatabase
import br.com.mkgcriacoes.cartaodevisita.repository.CartoVisitaRepository

class App: Application() {
    val db by lazy { AppDatabase.getDb(this) }

    val cartaoVisitaRepository by lazy {
        CartoVisitaRepository(db.cartaoVisitaDao())
    }
}