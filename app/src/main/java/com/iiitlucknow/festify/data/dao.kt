package com.iiitlucknow.festify.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface dao {
    @Insert
    suspend fun addevent(myEvents: my_events)

    @Delete
    suspend fun  deleteevent(myEvents: my_events)

    @Query("SELECT * FROM words_table ORDER BY id ASC")
    fun getevents(): LiveData<MutableList<my_events>>

}