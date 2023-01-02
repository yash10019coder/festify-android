package com.iiitlucknow.android.festify.home

import android.app.Application
import android.provider.CalendarContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ViewModelScoped
class HomeViewModel @Inject constructor(
    application: Application,
    dao: EventsDao
) : AndroidViewModel(application) {
    private val myrepo: EventsRepository
    val allwords: LiveData<MutableList<CalendarContract.EventsEntity>>

    init {
        myrepo = EventsRepository(dao)
        allwords = myrepo.getEventItems
    }

    fun addevent(eventsEntity: CalendarContract.EventsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.insertEventItem(eventsEntity)
        }
    }

    fun deleteevent(eventsEntity: CalendarContract.EventsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.deleteEventItem(eventsEntity)
        }
    }
}
