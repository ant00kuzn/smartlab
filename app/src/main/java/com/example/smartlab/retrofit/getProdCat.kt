package com.example.smartlab.retrofit

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

suspend fun getProductsByCategory(categoryId: Int): State<List<Products>> {
    val productsState = mutableStateOf<List<Products>>(emptyList())

    return withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            RetrofitHelper.usersInterface.getProducts(
                catId = categoryId
            ).enqueue(object : Callback<List<Products>> {
                override fun onResponse(
                    call: Call<List<Products>>,
                    response: Response<List<Products>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { searchResult ->
                            val productList = searchResult.map {
                                Products(
                                    id = it.id,
                                    name = it.name,
                                    category_id = it.category_id,
                                    time = it.time,
                                    price = it.price
                                )
                            }
                            productsState.value = productList
                            continuation.resume(Unit)

                        }
                    } else {
                        Log.e("getProductsByCategory", "Response was not successful: ${response.errorBody()?.string()}")
                        productsState.value = emptyList()
                        continuation.resume(Unit)

                    }
                }

                override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                    Log.e("getProductsByCategory", "Network failure: ${t.message}", t)
                    productsState.value = emptyList()
                    continuation.resume(Unit)
                }
            })
        }
        productsState
    }
}