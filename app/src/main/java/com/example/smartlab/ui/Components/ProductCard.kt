package com.example.smartlab.ui.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.smartlab.dataClasses.Products
import com.example.smartlab.ui.theme.InputBGColor

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: Products) {
    Card(
        modifier = modifier.fillMaxWidth().height(136.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = InputBGColor)
    ) {
        Text(
            text = product.name,
            modifier = Modifier.fillMaxSize().weight(1f).padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = 16.sp,
            softWrap = true,
            maxLines = 2,
            lineHeight = 20.sp,
            letterSpacing = -0.032.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            color = Color.Black
        )

        Row(modifier = Modifier.weight(1f).fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            )
            {
                Text(
                    text = "${product.time} дня",
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color(0x80000000),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${product.price} ₽",
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(end = 1.dp)
                    .width(125.dp)
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
                    text = "Добавить",
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
private fun ProductCardPrev() {
    ProductCard(product = Products(1, "sadfas", 1, 32, "1800"))
}