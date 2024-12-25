package com.example.smartlab.code

import com.example.smartlab.dataClasses.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

private const val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuZWFzeHFnZnl0YnVqYmVhY3BxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzUwNzY0NzIsImV4cCI6MjA1MDY1MjQ3Mn0.5EVASZZ9M7nX3IwnbYCDEUd8IShIOSfGLb3dMx3E6AE"

interface UsersInterface {
    @Headers(
        "apikey: $apiKey"
    )
    @POST("auth/v1/verify")
    fun getUser(@Body user: User): Call<Void>
}