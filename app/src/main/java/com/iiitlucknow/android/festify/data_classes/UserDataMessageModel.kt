package com.iiitlucknow.android.festify.data_classes

data class UserDataMessageModel(
    val __v: Int,
    val _id: String,
    val registeredEvents: MutableList<String>,
    val token: Boolean,
    val userEmail: String,
    val userName: String,
    val userPassword: String,
    val userPhoto: String
)