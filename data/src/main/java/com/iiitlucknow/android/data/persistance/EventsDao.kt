package com.iiitlucknow.android.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDao {
    @Insert
    suspend fun addEvent(eventsEntity: EventsEntity)

    @Delete
    suspend fun deleteEvent(eventsEntity: EventsEntity)

    @Query("SELECT * FROM words_table ORDER BY id ASC")
    fun getEvents(): LiveData<MutableList<EventsEntity>>
}
