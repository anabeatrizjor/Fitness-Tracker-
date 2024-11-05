package com.example.fitnesstracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calculo_table")
data class Calculo(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val tipo: String,
    val resultado: String,
    val data: Date
)