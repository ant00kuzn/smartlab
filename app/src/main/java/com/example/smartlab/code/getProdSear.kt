package com.example.smartlab.code

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.smartlab.dataClasses.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun getProdSear(searchText: String): State<List<Products>> {
    val prod = mutableStateOf<List<Products>>(emptyList())
    return withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            RetrofitHelper.usersInterface.getProducts(searchText)
                .enqueue(object : Callback<List<Products>> {
                    override fun onResponse(
                        call: Call<List<Products>>,
                        response: Response<List<Products>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { product ->
                                prod.value = product
                                continuation.resume(Unit)
                            }
                        } else {
                            Log.e("getAct", "Response was not successful: ${response.errorBody()?.string()}")
                            prod.value = emptyList()
                            continuation.resume(Unit)
                        }
                    }

                    override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                        Log.e("getAct", "Failure getting actions: ${t.message}", t)
                        prod.value = emptyList()
                        continuation.resume(Unit)
                    }
                })
        }
        prod
    }
}