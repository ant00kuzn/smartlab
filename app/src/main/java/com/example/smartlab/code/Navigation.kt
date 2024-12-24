package com.example.smartlab.code

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.ui.Screens.Authorization

@Composable
fun Navigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboard"){
        composable("onboard"){ onBoardQueue(modifier = modifier.fillMaxSize(), navController) }
        composable("auth"){ Authorization() }
    }
}