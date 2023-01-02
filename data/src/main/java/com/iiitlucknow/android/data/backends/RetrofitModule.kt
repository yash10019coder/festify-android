package com.iiitlucknow.android.data.backends

import com.iiitlucknow.android.data.backends.api.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.text.Typography.dagger

@Module
@InstallIn(Singleton::class)
object RetrofitModule {
    private const val BASE_URL =
        "https://festify-iiitl.herokuapp.com/"
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val api: UserService by lazy {
        retrofit.create(UserService::class.java)
    }
}