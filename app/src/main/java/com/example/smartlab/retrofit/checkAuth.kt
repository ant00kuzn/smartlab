package com.example.smartlab.retrofit

import android.util.Log
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.UserData
import com.example.smartlab.dataClasses.checkUser
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun checkAuth(email: String, password: String): Boolean{
    var res: Boolean = true
    RetrofitHelper.usersInterface.checkAuth(UserData(email, password))
        .enqueue(object: Callback<ResponceCode> {
            override fun onResponse(p0: Call<ResponceCode>, p1: Response<ResponceCode>) {
                Log.v("dsafasdasdsd", p1.code().toString())
            }

            override fun onFailure(p0: Call<ResponceCode>, p1: Throwable) {
                Log.e("getCode", p1.message.toString())
                res = false
            }
        })

    return res
}