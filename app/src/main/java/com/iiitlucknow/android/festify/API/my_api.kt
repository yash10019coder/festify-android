package com.iiitlucknow.android.festify.API

import com.iiitlucknow.android.festify.data_classes.default_response
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface my_api {
    @FormUrlEncoded
    @POST("user/signup")
    fun createuser(
        @Field("userName") userName: String,
        @Field("userPassword") userPassword: String,
        @Field("userEmail") userEmail: String,
        @Field("userPhoto") userPhoto: String
    ): Call<default_response>

    @FormUrlEncoded
    @POST("event/create")
    fun addevent(
        @Field("eventName") eventName: String,
        @Field("eventCategory") eventCategory: String,
        @Field("eventDate") eventDate: String,
        @Field("eventEndDate") eventEndDate: String,
        @Field("eventStartTime") eventStartTime: String,
        @Field("eventEndTime") eventEndTime: String,
        @Field("eventDescription") eventDescription: String,
        @Field("eventImage") eventImage: String
    ): Call<default_response>

    @FormUrlEncoded
    @POST("user/login")
    fun loginuser(
        @Field("usernameOrEmail") userName: String,
        @Field("password") userPassword: String,
    ): Call<default_response>
}
