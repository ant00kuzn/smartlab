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
import com.example.smartlab.ui.theme.OnboardDescriptionColor

@Composable
fun OnboardDescription(modifier: Modifier = Modifier, text: String){
    Text(
        text = text,
        modifier = modifier,
        color = OnboardDescriptionColor,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
        lineHeight = 20.sp
    )
}

@Preview
@Composable
private fun OnboardDescriptionPreview() {
    OnboardDescription(text = "Описание")
}