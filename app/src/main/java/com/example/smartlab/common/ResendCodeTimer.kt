package com.example.smartlab.common

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.code.sendCode
import kotlinx.coroutines.delay

@Composable
fun ResendCodeTimer(email: String) {
    var secondsLeft by remember { mutableStateOf(60) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
        sendCode(email)
    }

    Text(
        text = if (secondsLeft == 0) "Письмо отправлено повторно" else "Отправить код повторно можно будет через $secondsLeft секунд",
        modifier = Modifier.width(242.dp),
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
        lineHeight = 20.sp,
        textAlign = TextAlign.Center,
        softWrap = true,
        color = Color(0xFF939396)
    )
}