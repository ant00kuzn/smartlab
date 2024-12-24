package com.example.smartlab.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.ui.Components.InputCodeFiled

@Composable
fun GetCode(modifier: Modifier = Modifier, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("reg") },
            modifier = Modifier.size(32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonColors(
                Color(0xFFF5F5F9),
                Color(0xFFF5F5F9),
                Color(0xFFF5F5F9),
                Color(0xFFF5F5F9)
            )
        ) {
            Icon(
                bitmap = ImageBitmap.imageResource(R.drawable.vector),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(132.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = "Введите код из E-mail",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(){
                InputCodeFiled()
                Spacer(modifier = Modifier.width(10.dp))
                InputCodeFiled()
                Spacer(modifier = Modifier.width(10.dp))
                InputCodeFiled()
                Spacer(modifier = Modifier.width(10.dp))
                InputCodeFiled()
                Spacer(modifier = Modifier.width(10.dp))
                InputCodeFiled()
                Spacer(modifier = Modifier.width(10.dp))
                InputCodeFiled()
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Отправить код повторно можно будет через" + "" + "секунд",
                modifier = Modifier.width(242.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                softWrap = true,
                color = Color(0xFF939396)
            )
        }
    }
}

@Preview
@Composable
private fun GetCodePreview() {
    GetCode(navController = rememberNavController())
}