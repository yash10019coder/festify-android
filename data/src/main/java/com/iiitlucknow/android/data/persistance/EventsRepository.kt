package com.iiitlucknow.android.data.persistance

import androidx.lifecycle.LiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EventsRepository
@Inject constructor(
    private val eventsDao: EventsDao,
) {
    suspend fun insertEventItem(eventsEntity: EventsEntity) {
        eventsDao.addEvent(eventsEntity)
    }

    suspend fun deleteEventItem(eventsEntity: EventsEntity) {
        eventsDao.deleteEvent(eventsEntity)
    }

    val getEventItems: LiveData<MutableList<EventsEntity>> = eventsDao.getEvents()
}
