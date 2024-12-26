package com.example.smartlab.code

import CreatePassword
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.ui.Screens.Authorization
import com.example.smartlab.ui.Screens.CreateCard
import com.example.smartlab.ui.Screens.GetCode
import com.example.smartlab.ui.Screens.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier, context: Context){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboard"){
        composable("onboard"){ onBoardQueue(modifier = modifier.fillMaxSize(), navController, context) }
        composable("auth"){
            Authorization(
                modifier = Modifier,
                textDescription = "Войдите, чтобы пользоваться функциями приложения",
                textUnderBut = "Еще нет аккаунта?",
                textLink = "Зарегистрироваться",
                navController = navController,
                context = context
            )
        }
        composable("reg"){
            Authorization(
                modifier = Modifier,
                textDescription = "Зарегистрируйтесь, чтобы пользоваться функциями приложения",
                textUnderBut = "Уже есть аккаунт?",
                textLink = "Войти",
                navController = navController,
                context = context
            )
        }
        composable("getCode") {
            GetCode(navController = navController, context = context)
        }
        composable("homeScreen"){
            HomeScreen(navController)
        }
        composable("createPassword"){
            CreatePassword(navController = navController, context)
        }
        composable("createCard") {
            CreateCard(navController = navController)
        }
    }
}