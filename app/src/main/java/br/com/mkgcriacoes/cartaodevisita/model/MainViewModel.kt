package br.com.mkgcriacoes.cartaodevisita.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mkgcriacoes.cartaodevisita.repository.CartoVisitaRepository
import java.lang.IllegalArgumentException

class MainViewModel (
    private val repository: CartoVisitaRepository
): ViewModel() {
    fun getAll(): LiveData<List<CartaoVisita>> = repository.getAll()

    fun getById(id: Long): LiveData<CartaoVisita> = repository.getById(id)

    fun insert(cartaoVisita: CartaoVisita){
        repository.insert(cartaoVisita)
    }

    fun delete(cartaoVisita: CartaoVisita){
        repository.delete(cartaoVisita)
    }
}

class MainViewModelFactory(
    private val repository: CartoVisitaRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Classe ViewModel Desconhecida!")
    }
}