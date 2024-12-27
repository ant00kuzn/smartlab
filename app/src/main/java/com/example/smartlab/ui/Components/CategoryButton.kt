package com.example.smartlab.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.ui.theme.AccentColor
import androidx.compose.ui.unit.sp

@Composable
fun CategoryButton(text: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) AccentColor else Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}