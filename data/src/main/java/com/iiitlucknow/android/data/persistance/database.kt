package com.iiitlucknow.android.data.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [my_events::class], version = 1, exportSchema = false)
abstract class database : RoomDatabase() {
    abstract fun myDao(): Dao
    companion object {
        @Volatile
        private var INSTANCE: database? = null
        fun getDatabase(context: Context): database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
