package com.iiitlucknow.android.data.backends.api

import com.iiitlucknow.android.data.backends.model.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    @FormUrlEncoded
    @POST("user/create")
    fun createUser(
        @Field("userName") userName: String,
        @Field("userPassword") userPassword: String,
        @Field("userEmail") userEmail: String,
        @Field("userPhoto") userPhoto: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("user/login")
    fun loginUser(
        @Field("usernameOrEmail") userName: String,
        @Field("password") userPassword: String,
    ): Call<DefaultResponse>
}
