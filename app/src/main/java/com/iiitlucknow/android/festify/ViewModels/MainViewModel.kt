package com.iiitlucknow.android.festify.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iiitlucknow.android.festify.API.my_api
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.API.retrofitInstance.api
import com.iiitlucknow.android.festify.data_classes.event_data
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _myweather = MutableLiveData<event_data>()
    val myname: LiveData<event_data> = _myweather
     init{
         getweather()
     }
    fun getweather() {

        viewModelScope.launch {
            try {
                _myweather.value =
                    retrofitInstance.api.getEventData()
                _status.value = "SUCCESS"


            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}