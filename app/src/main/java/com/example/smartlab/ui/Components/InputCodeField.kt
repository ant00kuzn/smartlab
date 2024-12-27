package com.example.smartlab.ui.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.retrofit.SendSrCode

@Composable
fun InputCodeFiled(codeLength: Int = 6, email: String, navController: NavController) {
    var code by remember { mutableStateOf("") }
    val focusRequesters = remember { List(codeLength) { FocusRequester() } }

    Row(modifier = Modifier.height(48.dp)) {
        for (i in 0 until codeLength) {
            OutlinedTextField(
                value = if (code.length > i) code[i].toString() else "",
                onValueChange = { newValue ->
                    code = if (newValue.length > 1){
                        code
                    }
                    else (if (newValue.all { it.isDigit() } ) {
                        val newCode = if (code.length > i){
                            code.substring(0,i) + newValue + (if (i + 1 < code.length) code.substring(i+1) else "")
                        } else  code + newValue

                        if (newCode.length >= codeLength){
                            SendSrCode(email, newCode, navController)
                        } else newCode
                    } else if (newValue.isEmpty() && code.length > i){
                        val newCode =  code.substring(0,i) + (if (i+1 < code.length) code.substring(i+1) else "")
                        newCode
                    } else code).toString()

                    if (code.length < codeLength && code.length > i){
                        focusRequesters.getOrNull(i + 1)?.requestFocus()
                    } else if (code.length <= i && code.isNotEmpty()) {
                        focusRequesters.getOrNull(i)?.requestFocus()
                    }
                },
                modifier = Modifier
                    .width(46.dp)
                    .focusRequester(focusRequesters[i]),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F9),
                    unfocusedContainerColor = Color(0xFFF5F5F9),
                    disabledContainerColor =  Color(0xFFF5F5F9),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
            if (i < codeLength - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun InputCodeFiledPreview() {
    InputCodeFiled(email = "", navController = rememberNavController())
}