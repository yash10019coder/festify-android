package com.iiitlucknow.android.data.backends.model

data class SignUpRequestBody(

    val userName: String,
    val userPassword: String,
    val userEmail: String,
    val userPhoto: String
)
