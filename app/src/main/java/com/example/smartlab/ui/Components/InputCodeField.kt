package com.example.smartlab.ui.Components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor

@Composable
fun InputCodeFiled(modifier: Modifier = Modifier, placeholder: String = "") {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        modifier = modifier
            .height(46.dp)
            .width(42.dp),
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier.height(46.dp).width(46.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 28.sp,
                letterSpacing = 0.038.sp
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = InputBGColor,
            unfocusedContainerColor = InputBGColor,
            focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
            unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
            focusedBorderColor = InputFocusedBorderColor,
            unfocusedBorderColor = InputStrokeColor,
            cursorColor = AccentColor
        ),
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
private fun InputCodeFiledPreview() {
    InputCodeFiled()
}