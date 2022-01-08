package com.iiitlucknow.android.data.persistance

import androidx.lifecycle.LiveData
import com.iiitlucknow.android.data.persistance.Dao
import com.iiitlucknow.android.data.persistance.my_events

class Repo(private val dao: Dao) {
    suspend fun insertitem(myEvents: my_events) {
        dao.addevent(myEvents)
    }

    suspend fun deleteword(myEvents: my_events) {
        dao.deleteevent(myEvents)
    }
    val getwords: LiveData<MutableList<my_events>> = dao.getevents()
}
