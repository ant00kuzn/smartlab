package com.example.smartlab.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ClearIcon(
    tint: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Filled.Clear,
        contentDescription = "clear",
        tint = tint,
        modifier = modifier
    )
}