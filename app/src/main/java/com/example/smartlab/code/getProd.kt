package com.example.smartlab.code

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.smartlab.dataClasses.EmailSen
import com.example.smartlab.dataClasses.ResponceCode
import com.example.smartlab.dataClasses.SearchResult
import com.example.smartlab.dataClasses.User
import com.example.smartlab.dataClasses.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

fun getProd(query: String){
    val quey = "*${query}*"
    RetrofitHelper.usersInterface.getProducts(quey)
        .enqueue(object: Callback<SearchResult> {
            override fun onResponse(p0: Call<SearchResult>, p1: Response<SearchResult>) {
                if (p1.isSuccessful){

                } else{

                }
            }

            override fun onFailure(p0: Call<SearchResult>, p1: Throwable) {
                Log.e("getCode", p1.message.toString())
            }
        })
}