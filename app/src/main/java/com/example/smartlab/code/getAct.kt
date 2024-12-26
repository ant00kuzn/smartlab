package com.example.smartlab.code

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.smartlab.dataClasses.Actions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getAct() {
    val act: SnapshotStateList<Actions>
    RetrofitHelper.usersInterface.getActions()
        .enqueue(object: Callback<List<Actions>> {
            override fun onResponse(p0: Call<List<Actions>>, p1: Response<List<Actions>>) {
                p1.body().let { action ->
//                    act.clear()
//                    if (action != null) {
//                        act.addAll(action)
//                    }
                }
            }

            override fun onFailure(p0: Call<List<Actions>>, p1: Throwable) {
                Log.e("getCode", p1.message.toString())
            }
        })
}