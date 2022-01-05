package com.iiitlucknow.android.festify.API

import com.iiitlucknow.android.festify.data_classes.default_response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface my_api {
    @FormUrlEncoded
    @POST("user/create")
    fun createuser(
        @Field("userName") userName: String,
        @Field("userPassword") userPassword: String,
        @Field("userEmail") userEmail: String,
        @Field("userPhoto") userPhoto: String
    ): Call<default_response>
}