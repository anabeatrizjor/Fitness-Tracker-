package com.example.fitnesstracker.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time // Convert Date to Long (milliseconds)
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) } // Convert Long back to Date
    }
}
