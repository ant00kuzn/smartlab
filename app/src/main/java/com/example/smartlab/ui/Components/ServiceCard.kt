package com.example.smartlab.ui.Components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.dataClasses.ServiceItem
import com.example.smartlab.ui.theme.InputBGColor

@Composable
fun ServiceCard(service: ServiceItem) {
    Card(
        modifier = Modifier.width(335.dp).height(136.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = InputBGColor)
    ) {
        Text(
            text = service.name,
            modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp),
            fontSize = 16.sp,
            softWrap = true,
            maxLines = 2,
            lineHeight = 20.sp,
            letterSpacing = -0.032.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold))
        )

        Row(modifier = Modifier.weight(1f).width(335.dp).fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            )
            {
                Text(
                    text = "${service.days}",
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color(0x80000000),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${service.price} ₽",
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(end = 1.dp)
                    .width(114.dp)
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF1A6FEE),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF1A6FEE),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = service.buttonText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color.White,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun ServiceCardPrev() {
    ServiceCard(service = ServiceItem("sadfas", "32 fsdf", "1800", "Добавить"))
}