package com.iiitlucknow.android.festify.API

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL =
    "https://festify.onrender.com/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val okhttp = okhttp3.OkHttpClient.Builder()
    .addInterceptor(OkHttpProfilerInterceptor())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okhttp)
    .baseUrl(BASE_URL)
    .build()

object retrofitInstance {
    val api: my_api by lazy {
        retrofit.create(my_api::class.java)
    }
}
