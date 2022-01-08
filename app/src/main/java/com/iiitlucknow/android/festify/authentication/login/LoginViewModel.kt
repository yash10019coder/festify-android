package com.iiitlucknow.android.festify.authentication.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.data.backends.RetrofitInstance
import com.iiitlucknow.android.data.backends.model.DefaultResponse
import com.iiitlucknow.android.data.backends.model.LoginData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var log_Response: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun checkLogin(loginData: LoginData) {
        RetrofitInstance.api.loginUser(
            loginData.usernameOrEmail,
            loginData.password
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                log_Response.value = response
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
