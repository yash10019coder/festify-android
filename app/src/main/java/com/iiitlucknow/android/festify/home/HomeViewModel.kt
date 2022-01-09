package com.iiitlucknow.android.festify.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.iiitlucknow.android.data.persistance.EventsDao
import com.iiitlucknow.android.data.persistance.EventsEntity
import com.iiitlucknow.android.data.persistance.EventsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class HomeViewModel @Inject constructor(
    application: Application,
    dao: EventsDao
) : AndroidViewModel(application) {
    private val myrepo: EventsRepository
    val allwords: LiveData<MutableList<EventsEntity>>

    init {
        myrepo = EventsRepository(dao)
        allwords = myrepo.getEventItems
    }

    fun addevent(eventsEntity: EventsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.insertEventItem(eventsEntity)
        }
    }

    fun deleteevent(eventsEntity: EventsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.deleteEventItem(eventsEntity)
        }
    }
}
