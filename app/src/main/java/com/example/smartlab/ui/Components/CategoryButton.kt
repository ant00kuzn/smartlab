package com.example.smartlab.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.ui.theme.AccentColor
import androidx.compose.ui.unit.sp

@Composable
fun CategoryButton(text: String, onClick: () -> Unit, isSelected: Boolean = false) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp).height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF1A6FEE) else Color(0xFFF5F5F9),
            contentColor = if (isSelected) Color.White else Color(0xFF7E7E9A)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview
@Composable
private fun CategoryButtonPrev() {
    CategoryButton(text = "Популярное", {})
}