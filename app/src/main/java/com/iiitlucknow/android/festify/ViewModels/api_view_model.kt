package com.iiitlucknow.android.festify.ViewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.default_response
import com.iiitlucknow.android.festify.data_classes.my_post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class api_view_model(application: Application) : AndroidViewModel(application) {
    var myResponse: MutableLiveData<Response<default_response>> = MutableLiveData()
    fun pushPost(post: my_post) {
        retrofitInstance.api.createuser(
            post.userName,
            post.userPassword,
            post.userEmail,
            post.userPhoto
        ).enqueue(object : Callback<default_response> {
            override fun onResponse(
                call: Call<default_response>,
                response: Response<default_response>
            ) {
                myResponse.value = response
            }

            override fun onFailure(call: Call<default_response>, t: Throwable) {
                Toast.makeText(getApplication(), t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
