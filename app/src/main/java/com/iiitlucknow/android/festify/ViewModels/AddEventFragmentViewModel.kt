package com.iiitlucknow.android.festify.ViewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.add_event_data
import com.iiitlucknow.android.festify.data_classes.default_response
import com.iiitlucknow.android.festify.data_classes.login_data
import dagger.hilt.android.internal.Contexts.getApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEventFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var add_Response: MutableLiveData<Response<default_response>> = MutableLiveData()
    fun checkAddEvent(addEventData: add_event_data) {
        retrofitInstance.api.addevent(
            addEventData.eventName,
                    addEventData.eventCategory,
                    addEventData.eventDate,
                    addEventData.eventEndDate,
                    addEventData.eventStartTime,
                    addEventData.eventEndTime,
                    addEventData.eventDescription,
                    addEventData.eventImage
        ).enqueue(object : Callback<default_response> {
            override fun onResponse(
                call: Call<default_response>,
                response: Response<default_response>
            ) {
                add_Response.value = response
            }

            override fun onFailure(call: Call<default_response>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}