package com.iiitlucknow.festify

import androidx.lifecycle.LiveData
import com.iiitlucknow.festify.data.dao
import com.iiitlucknow.festify.data.my_events

class repo(private val dao: dao) {
    suspend fun insertitem(myEvents: my_events){
        dao.addevent(myEvents)
    }

    suspend fun deleteword(myEvents: my_events){
        dao.deleteevent(myEvents)
    }
    val getwords: LiveData<MutableList<my_events>> = dao.getevents()
}