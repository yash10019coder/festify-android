package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.DefaultResponse
import com.iiitlucknow.android.festify.data_classes.MyPost
import com.iiitlucknow.android.festify.data_classes.regEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(application: Application):AndroidViewModel(application) {
    var regResponse: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun pushPost(post: regEvent) {
        retrofitInstance.api.eventRegister(
            post.userName,
            post.userEvent
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                regResponse.value = response
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}