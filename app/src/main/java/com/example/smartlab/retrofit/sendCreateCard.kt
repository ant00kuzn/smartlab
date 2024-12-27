package com.example.smartlab.retrofit

import android.util.Log
import com.example.smartlab.dataClasses.PatientCard
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun sendCreateCard(patient: PatientCard){
    RetrofitHelper.usersInterface.setUserCard(patient)
        .enqueue(object: Callback<ResponceCode> {
            override fun onResponse(p0: Call<ResponceCode>, p1: Response<ResponceCode>) {
                if (p1.isSuccessful){
                    Log.v("setUser", "User was set with ${patient}")
                } else(
                    Log.v("Error set user", "${p1}")
                )
            }

            override fun onFailure(p0: Call<ResponceCode>, p1: Throwable) {
                Log.e("getCode", p1.message.toString())
            }
        })
}