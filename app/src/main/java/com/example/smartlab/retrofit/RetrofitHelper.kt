package com.example.smartlab.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy


object RetrofitHelper {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hneasxqgfytbujbeacpq.supabase.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val usersInterface: UsersInterface = retrofit.create(UsersInterface::class.java)
}