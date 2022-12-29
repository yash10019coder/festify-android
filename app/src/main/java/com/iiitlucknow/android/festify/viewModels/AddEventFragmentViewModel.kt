package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.AddEventData
import com.iiitlucknow.android.festify.data_classes.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEventFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var addResponse: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun checkAddEvent(addEventData: AddEventData) {
        retrofitInstance.api.addevent(
            addEventData.eventName,
                    addEventData.eventCategory,
                    addEventData.eventDate,
                    addEventData.eventEndDate,
                    addEventData.eventStartTime,
                    addEventData.eventEndTime,
                    addEventData.eventDescription,
                    addEventData.eventImage
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                addResponse.value = response
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}