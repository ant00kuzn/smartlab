package com.example.smartlab.ui.Screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.code.SendSrCode
import com.example.smartlab.common.ResendCodeTimer
import com.example.smartlab.ui.Components.InputCodeFiled
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import java.util.Vector

@Composable
fun GetCode(modifier: Modifier = Modifier, navController: NavController, context: Context) {
    var text by remember { mutableStateOf("") }

    val preferencesManager = remember { PreferencesManager(context) }
    val email = remember { mutableStateOf(preferencesManager.getData("email", "")) }
    val password = remember { mutableStateOf(preferencesManager.getData("passHash", "")) }

    Column(modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal=20.dp)){
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("auth") },
            modifier = Modifier.size(32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = buttonColors(
                contentColor = Color.Black,
                containerColor = Color(0xFFF5F5F9)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(132.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = "Введите код из E-mail",
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            InputCodeFiled(email = email.value, navController = navController)

            Spacer(modifier = Modifier.height(20.dp))

            ResendCodeTimer(email.value, password.value)
        }
    }
}

@Preview
@Composable
private fun GetCodePreview() {
    GetCode(navController = rememberNavController(), context = LocalContext.current)
}