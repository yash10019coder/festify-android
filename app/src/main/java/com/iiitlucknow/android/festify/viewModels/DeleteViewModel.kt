package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.DefaultResponse
import com.iiitlucknow.android.festify.data_classes.sendEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteViewModel(application: Application):AndroidViewModel(application) {
    var delResponse: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun delPost(post: sendEvent) {
        retrofitInstance.api.eventRegister(
            post.userName,
            post.eventId
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                delResponse.value = response
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}