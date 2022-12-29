package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.EventData
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _myevent = MutableLiveData<EventData>()
    val myevent: LiveData<EventData> = _myevent


    init {
        getevent()
    }

    fun getevent() {

        viewModelScope.launch {
            try {
                _myevent.value =
                    retrofitInstance.api.getEventData()
                _status.value = "SUCCESS"


            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }

    }
}