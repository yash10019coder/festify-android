package com.iiitlucknow.android.festify.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iiitlucknow.android.festify.API.retrofitInstance
import com.iiitlucknow.android.festify.data_classes.UserData
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _myUserData = MutableLiveData<UserData>()
    val myUserData: LiveData<UserData> = _myUserData

    init {
        get_data()
    }

    fun get_data() {

        viewModelScope.launch {
            try {
                _myUserData.value =
                    retrofitInstance.api.getUserData()
                _status.value = "SUCCESS"


            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = "Failure: ${e.message}"

            }
        }

    }
}