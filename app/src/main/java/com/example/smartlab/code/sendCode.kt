package com.example.smartlab.code

import android.util.Log
import com.example.smartlab.dataClasses.EmailSen
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun sendCode(email: String){
    val em = EmailSen(email = email)
    RetrofitHelper.usersInterface.getCode(email = em)
        .enqueue(object: Callback<ResponceCode> {
            override fun onResponse(p0: Call<ResponceCode>, p1: Response<ResponceCode>) {
                if (p1.isSuccessful){
                    Log.v("code", "code was sent on ${email}")
                }
                else(
                    Log.v("err code", "${p1}")
                )
            }

            override fun onFailure(p0: Call<ResponceCode>, p1: Throwable) {
                Log.e("getCode", p1.message.toString())
            }
        })
}