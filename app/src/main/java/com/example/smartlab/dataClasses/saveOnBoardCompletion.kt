package com.example.smartlab.dataClasses

import android.content.Context
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    suspend fun setBoolean(key: String, value: Boolean) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }
    }

    suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return withContext(Dispatchers.IO){
            sharedPreferences.getBoolean(key, defaultValue)
        }
    }
}