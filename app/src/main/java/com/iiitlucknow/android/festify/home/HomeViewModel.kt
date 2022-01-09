package com.iiitlucknow.android.festify.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.iiitlucknow.android.data.persistance.EventsTable
import com.iiitlucknow.android.data.persistance.PersistanceDatabase
import com.iiitlucknow.android.data.persistance.Repo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class HomeViewModel @Inject constructor(
    application: Application,
    dao: PersistanceDatabase
) : AndroidViewModel(application) {
    private val myrepo: Repo
    val allwords: LiveData<MutableList<EventsTable>>

    init {
        myrepo = Repo(dao)
        allwords = myrepo.getwords
    }

    fun addevent(eventsTable: EventsTable) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.insertitem(eventsTable)
        }
    }

    fun deleteevent(eventsTable: EventsTable) {
        viewModelScope.launch(Dispatchers.IO) {
            myrepo.deleteword(eventsTable)
        }
    }
}
