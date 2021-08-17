package br.com.mkgcriacoes.cartaodevisita.repository

import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CartoVisitaRepository(
    private val dao: CartaoVisitaDao
) {
    fun getAll() = dao.getAll()

    fun insert(cartaoVisita: CartaoVisita) = runBlocking {
        launch(Dispatchers.IO){
            dao.insert(cartaoVisita)
        }
    }
}