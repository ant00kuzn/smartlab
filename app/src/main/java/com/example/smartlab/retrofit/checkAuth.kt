package com.example.smartlab.retrofit

import android.util.Log
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.UserData
import com.example.smartlab.dataClasses.checkUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

suspend fun checkAuth(email: String, password: String): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitHelper.usersInterface.checkAuth(UserData(email, password)).execute()
            if (response.isSuccessful && response.body() != null && response.code() == 200){
                true
            } else {
                false
            }
        } catch (e: Exception) {
            Log.e("Auth", "Error during authorization: ${e.message}")
            throw e
        }
    }
}