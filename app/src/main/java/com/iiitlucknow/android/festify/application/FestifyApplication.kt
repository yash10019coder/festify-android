package com.iiitlucknow.android.festify.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FestifyApplication
@Inject constructor() : Application()
