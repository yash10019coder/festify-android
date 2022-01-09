package com.iiitlucknow.android.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventsTable::class], version = 1, exportSchema = false)
abstract class PersistanceDatabase : RoomDatabase() {
    abstract fun myDao(): Dao
}
