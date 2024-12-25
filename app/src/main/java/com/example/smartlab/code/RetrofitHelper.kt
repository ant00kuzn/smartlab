package com.example.smartlab.code

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy


object RetrofitHelper {
    val proxy: Proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("10.207.106.38", 3128))
    val httpClient: OkHttpClient = OkHttpClient.Builder().proxy(proxy).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hneasxqgfytbujbeacpq.supabase.co")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val usersInterface: UsersInterface = retrofit.create(UsersInterface::class.java)
}