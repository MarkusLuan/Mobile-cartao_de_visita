package br.com.mkgcriacoes.cartaodevisita.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mkgcriacoes.cartaodevisita.repository.CartoVisitaRepository

class MainViewModel (
    private val repository: CartoVisitaRepository
): ViewModel(), ViewModelProvider.Factory {
    fun insert(cartaoVisita: CartaoVisita){
        repository.insert(cartaoVisita)
    }

    fun getAll(): LiveData<List<CartaoVisita>> = repository.getAll()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return this as T
    }
}