package com.example.dandelion.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DayLog::class], version = 1, exportSchema = false)
abstract class DandelionDatabase : RoomDatabase() {
    abstract fun logDao(): DayLogDao
    companion object {
        @Volatile
        private var Instance: DandelionDatabase? = null

        fun getDatabase(context: Context): DandelionDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DandelionDatabase::class.java, "dayLog_database")
                    .fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}