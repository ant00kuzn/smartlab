package com.example.smartlab.retrofit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.smartlab.dataClasses.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun getCat(): State<List<Categories>> {
    val cat = mutableStateOf<List<Categories>>(emptyList())
    return withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            RetrofitHelper.usersInterface.getCat()
                .enqueue(object : Callback<List<Categories>> {
                    override fun onResponse(
                        call: Call<List<Categories>>,
                        response: Response<List<Categories>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { category ->
                                cat.value = category
                                continuation.resume(Unit)
                            }
                        } else {
                            Log.e("getAct", "Response was not successful: ${response.errorBody()?.string()}")
                            cat.value = emptyList()
                            continuation.resume(Unit)
                        }
                    }

                    override fun onFailure(call: Call<List<Categories>>, t: Throwable) {
                        Log.e("getAct", "Failure getting actions: ${t.message}", t)
                        cat.value = emptyList()
                        continuation.resume(Unit)
                    }
                })
        }
        cat
    }
}