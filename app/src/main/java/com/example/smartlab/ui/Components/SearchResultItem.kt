package com.example.smartlab.ui.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.InputBGColor

@Composable
fun SearchResultItem(text: String) {
    Card(modifier = Modifier
            .fillMaxWidth ()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = InputBGColor
        ))
    {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            fontSize = 16.sp)
    }
}