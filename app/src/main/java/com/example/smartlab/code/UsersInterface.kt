package com.example.smartlab.code

import com.example.smartlab.dataClasses.Actions
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.dataClasses.Products
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.SearchResult
import com.example.smartlab.dataClasses.UserData
import com.example.smartlab.dataClasses.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

private const val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhuZWFzeHFnZnl0YnVqYmVhY3BxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzUwNzY0NzIsImV4cCI6MjA1MDY1MjQ3Mn0.5EVASZZ9M7nX3IwnbYCDEUd8IShIOSfGLb3dMx3E6AE"

interface UsersInterface {
    //registration
    @Headers(
        "apikey: $apiKey"
    )
    @POST("auth/v1/signup")
    fun setUser(@Body user: UserData): Call<ResponceCode>

    @Headers(
        "apikey: $apiKey"
    )
    @POST("auth/v1/verify")
    fun getConfirmAuth(@Body user: User): Call<ResponceCode>

    //auth
    @Headers(
        "apikey: $apiKey"
    )
    @POST("auth/v1/token?grant_type=password")
    fun checkAuth(@Body user: UserData): Call<ResponceCode>

    //category product
    @Headers(
        "apikey: $apiKey"
    )
    @GET("rest/v1/products?select=*")
    fun getProducts(@Query("category_id") catId: Int): Call<List<Products>>

    //search product
    @Headers(
        "apikey: $apiKey"
    )
    @GET("rest/v1/products?select=*")
    fun getProducts(@Query("name") search: String): Call<List<Products>>

    //products
    @Headers(
        "apikey: $apiKey"
    )
    @GET("rest/v1/products?select=*")
    fun getProducts(): Call<List<Products>>

    //categories
    @Headers(
        "apikey: $apiKey"
    )
    @GET("rest/v1/categories?select=*")
    fun getCat(): Call<List<Categories>>

    //actions
    @Headers(
        "apikey: $apiKey"
    )
    @GET("rest/v1/actions?select=*")
    fun getActions(): Call<List<Actions>>
}