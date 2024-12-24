package com.example.smartlab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.smartlab.code.Navigation
import com.example.smartlab.ui.theme.SmartlabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            SmartlabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){innerPadding ->
                    Navigation(modifier = Modifier.fillMaxSize().padding(innerPadding))
                }
            }
        }
    }
}