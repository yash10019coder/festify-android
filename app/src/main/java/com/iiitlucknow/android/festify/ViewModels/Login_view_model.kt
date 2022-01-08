package com.iiitlucknow.android.festify.ViewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.default_response
import com.iiitlucknow.android.festify.data_classes.login_data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_view_model(application: Application) : AndroidViewModel(application) {

    var log_Response: MutableLiveData<Response<default_response>> = MutableLiveData()
    fun checkLogin(loginData: login_data) {
        retrofitInstance.api.loginuser(
            loginData.usernameOrEmail,
            loginData.password
        ).enqueue(object : Callback<default_response> {
            override fun onResponse(
                call: Call<default_response>,
                response: Response<default_response>
            ) {
                log_Response.value = response
            }

            override fun onFailure(call: Call<default_response>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
