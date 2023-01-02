package com.iiitlucknow.android.festify.authentication.signup

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.data.backends.RetrofitModule
import com.iiitlucknow.android.data.backends.model.DefaultResponse
import com.iiitlucknow.android.data.backends.model.SignUpRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    var sign_Response: MutableLiveData<Response<DefaultResponse>> = MutableLiveData()
    fun pushPost(post: SignUpRequestBody) {
        RetrofitModule.api.createUser(
            post.userName,
            post.userPassword,
            post.userEmail,
            post.userPhoto
        ).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                sign_Response.value = response
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
