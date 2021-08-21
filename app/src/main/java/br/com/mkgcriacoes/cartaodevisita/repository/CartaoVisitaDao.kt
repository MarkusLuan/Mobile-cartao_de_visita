package br.com.mkgcriacoes.cartaodevisita.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita

@Dao
interface CartaoVisitaDao {
    @Query("SELECT * FROM CartaoVisita WHERE id = :id")
    fun getById(id: Long): LiveData<CartaoVisita>

    @Query("SELECT * FROM CartaoVisita")
    fun getAll(): LiveData<List<CartaoVisita>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartaoVisita: CartaoVisita)

    @Delete
    suspend fun delete(cartaoVisita: CartaoVisita)
}