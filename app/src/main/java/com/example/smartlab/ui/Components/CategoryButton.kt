package com.example.smartlab.ui.Components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.InputBGColor

@Composable
fun CategoryButton(text: String) {
    Button(
        onClick = {},
        modifier = Modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = InputBGColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF7E7E9A),
            fontFamily = FontFamily(Font(R.font.nunito_semibold))
        )
    }
}

@Preview
@Composable
private fun CategoryButtonPrev() {
    CategoryButton(text = "dashfdhbasf")
}