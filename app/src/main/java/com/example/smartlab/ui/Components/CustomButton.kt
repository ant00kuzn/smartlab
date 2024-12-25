package com.example.smartlab.ui.Components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R

@SuppressLint("RememberReturnType")
@Composable
fun CustBut(modifier: Modifier = Modifier, text: String) {
    Button(
        onClick = {},
        modifier = Modifier
            .size(80.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonColors(
            contentColor = Color.Black,
            containerColor = Color(0xFFF5F5F9),
            disabledContentColor = Color.Black,
            disabledContainerColor = Color(0xFFF5F5F9)
        )
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            letterSpacing = 0.033.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun CustButPrev() {
    CustBut(text = "1")
}