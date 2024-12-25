package com.example.smartlab.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.AccentColor

@Composable
fun OnboardHeader(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        color = AccentColor,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.nunito_semibold))
    )
}

@Preview
@Composable
private fun OnboardHeaderPreview() {
    OnboardHeader(text = "Заголовок")
}
