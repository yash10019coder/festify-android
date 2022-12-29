package com.iiitlucknow.android.festify.API

import com.iiitlucknow.android.festify.data_classes.DefaultResponse
import com.iiitlucknow.android.festify.data_classes.EventData
import com.iiitlucknow.android.festify.data_classes.UserData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface my_api {
    @FormUrlEncoded
    @POST("user/signup")
    fun createuser(
        @Field("userName") userName: String,
        @Field("userPassword") userPassword: String,
        @Field("userEmail") userEmail: String,
        @Field("userPhoto") userPhoto: String
    ): Call<DefaultResponse>

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
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("user/login")
    fun loginuser(
        @Field("usernameOrEmail") userName: String,
        @Field("password") userPassword: String,
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("/verify")
    fun verify_user(
        @Field("userName") userName: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("wing") wing: String,
        @Field("role") role: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("user/register")
    fun eventRegister(
        @Field("userName") userName: String,
        @Field("eventName") eventName: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("user/delete")
    fun eventDelete(
        @Field("userName") userName: String,
        @Field("eventName") eventId: String
    ): Call<DefaultResponse>

    @GET("user/ro")
    suspend fun getUserData(): UserData

    @GET("event/all")
    suspend fun getEventData(): EventData
}
