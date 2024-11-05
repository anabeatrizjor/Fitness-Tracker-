package com.example.fitnesstracker.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters

@Database(entities = [Calculo::class], version = 1)
@TypeConverters(Converters::class)
abstract class CalculoDataBase : RoomDatabase() {
    abstract fun calculoDao(): CalculoDao

    companion object {
        @Volatile
        private var INSTANCE: CalculoDataBase? = null

        fun getDataBase(context: Context): CalculoDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalculoDataBase::class.java,
                    "calculo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
