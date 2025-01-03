package com.example.smartlab.code

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.smartlab.R
import com.example.smartlab.dataClasses.PageInfo
import com.example.smartlab.ui.Screens.OnBoard

@Composable
fun onBoardQueue(modifier: Modifier = Modifier, navController: NavController, context: Context) {
    val pagerState = rememberPagerState{3}
    val preferencesManager = remember { PreferencesManager(context) }
    val isCompleted by remember { mutableStateOf<Boolean?>(preferencesManager.getData("isCompleted", false.toString()).toBoolean()) }

    if (isCompleted == null){
        return
    }
    if (isCompleted == true) {
        navController.navigate("auth")
        return
    }

    val pagesInQueue = listOf(
        PageInfo(
            dataText = "Пропустить",
            dataHeaderText = "Анализы",
            dataDescriptionText = "Экспресс сбор и получение проб",
            dotImg = R.drawable.dot1,
            onBoardImg = R.drawable.onboard1
        ),
        PageInfo(
            dataText = "Пропустить",
            dataHeaderText = "Уведомления",
            dataDescriptionText = "Вы быстро узнаете о результатах",
            dotImg = R.drawable.dot2,
            onBoardImg = R.drawable.onboard2
        ),
        PageInfo(
            dataText = "Завершить",
            dataHeaderText = "Мониторинг",
            dataDescriptionText = "Наши врачи всегда наблюдают \n" +
                    "за вашими показателями здоровья",
            dotImg = R.drawable.dot3,
            onBoardImg = R.drawable.onboard3
        )
    )


    HorizontalPager(state = pagerState, Modifier.fillMaxWidth()) { page ->
         OnBoard(
                modifier = modifier,
                navController = navController,
                buttonText = pagesInQueue[page].dataText,
                onBoardHeaderText = pagesInQueue[page].dataHeaderText,
                onBoardDescriptionText = pagesInQueue[page].dataDescriptionText,
                dot = pagesInQueue[page].dotImg,
                onBoard = pagesInQueue[page].onBoardImg
         )

    }
}