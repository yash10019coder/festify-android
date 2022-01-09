package com.iiitlucknow.android.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EventsEntity::class], version = 1, exportSchema = false)
abstract class EventsDatabase : RoomDatabase() {
    abstract fun eventsPersistanceDao(): EventsDao
}
