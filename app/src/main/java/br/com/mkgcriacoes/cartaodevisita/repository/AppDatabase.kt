package br.com.mkgcriacoes.cartaodevisita.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mkgcriacoes.cartaodevisita.model.CartaoVisita

@Database(entities = [CartaoVisita::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        @Volatile
        private var instacia: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
             return instacia ?: synchronized(this){
                 val instancia = Room.databaseBuilder(
                     context.applicationContext,
                     AppDatabase::class.java,
                     "cartao_visita_db"
                 ).build()
                 this.instacia = instancia
                 instancia
             }
        }
    }

    abstract fun cartaoVisitaDao(): CartaoVisitaDao
}