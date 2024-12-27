package com.example.smartlab.retrofit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.smartlab.dataClasses.Actions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun getAct(): State<List<Actions>> {
    val act = mutableStateOf<List<Actions>>(emptyList())
    return withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            RetrofitHelper.usersInterface.getActions()
                .enqueue(object : Callback<List<Actions>> {
                    override fun onResponse(
                        call: Call<List<Actions>>,
                        response: Response<List<Actions>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { actions ->
                                act.value = actions
                                continuation.resume(Unit)
                            }
                        } else {
                            Log.e("getAct", "Response was not successful: ${response.errorBody()?.string()}")
                            act.value = emptyList()
                            continuation.resume(Unit)
                        }
                    }

                    override fun onFailure(call: Call<List<Actions>>, t: Throwable) {
                        Log.e("getAct", "Failure getting actions: ${t.message}", t)
                        act.value = emptyList()
                        continuation.resume(Unit)
                    }
                })
        }
        act
    }
}