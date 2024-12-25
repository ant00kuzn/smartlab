package com.example.smartlab.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.ui.Components.CustBut

@Composable
fun CreatePassword(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Пропустить",
            modifier = modifier
                .clickable { navController.navigate("createCard") }
                .align(Alignment.End),
            color = Color(0xFF1A6FEE),
            fontSize = 15.sp,
            //fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            lineHeight = 20.sp,
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Создайте пароль",
            color = Color.Black,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.033.sp,
            //fontFamily = FontFamily(Font(R.font.nunito_bold))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Для защиты ваших персональных данных",
            color = Color(0xFF939396),
            fontSize = 15.sp,
            lineHeight = 20.sp,
            //fontFamily = FontFamily(Font(R.font.nunito_semibold))
        )

        Spacer(modifier = Modifier.height(56.dp))

        //progress

        Spacer(modifier = Modifier.height(60.dp))

        Column(modifier = Modifier.size(288.dp), verticalArrangement = Arrangement.SpaceBetween){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustBut(text = "1")
                CustBut(text = "2")
                CustBut(text = "3")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustBut(text = "4")
                CustBut(text = "5")
                CustBut(text = "6")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustBut(text = "7")
                CustBut(text = "8")
                CustBut(text = "9")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(){
            CustBut(text = "0")

            Icon(
                painter = BitmapPainter(ImageBitmap.imageResource(id = R.drawable.del_icon)),
                contentDescription = null,
                modifier = Modifier.width(35.dp).height(24.dp).clickable {

                }
            )
        }
    }
}

@Preview
@Composable
private fun CreatePasswordPRev() {
    CreatePassword(navController = rememberNavController())
}