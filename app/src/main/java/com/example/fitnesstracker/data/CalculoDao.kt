package com.example.fitnesstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculoDao {
    @Insert
    suspend fun inserir(calculo: Calculo)

    @Query("SELECT * FROM calculo_table ORDER BY data DESC")
    suspend fun getTodosCalculos(): List<Calculo>
}