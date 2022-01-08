package com.iiitlucknow.android.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Insert
    suspend fun addevent(myEvents: my_events)

    @Delete
    suspend fun deleteevent(myEvents: my_events)

    @Query("SELECT * FROM words_table ORDER BY id ASC")
    fun getevents(): LiveData<MutableList<my_events>>
}
