package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.DefaultResponse
import com.iiitlucknow.android.festify.data_classes.VerifyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestVerificationViewModel(application: Application):AndroidViewModel(application) {
    var verifyResponse: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun verify(verifyData: VerifyData) {
        retrofitInstance.api.verify_user(
            verifyData.userName,
            verifyData.name,
            verifyData.email,
            verifyData.wing,
            verifyData.role
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                verifyResponse.value = response
               Toast.makeText(getApplication(), response.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}