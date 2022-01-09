package com.iiitlucknow.android.data.persistance

import androidx.lifecycle.LiveData

class Repo(private val dao: Dao) {
    suspend fun insertitem(eventsTable: EventsTable) {
        dao.addevent(eventsTable)
    }

    suspend fun deleteword(eventsTable: EventsTable) {
        dao.deleteevent(eventsTable)
    }
    val getwords: LiveData<MutableList<EventsTable>> = dao.getevents()
}
