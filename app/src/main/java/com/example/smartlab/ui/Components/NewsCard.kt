package com.example.smartlab.ui.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.dataClasses.Actions
import com.example.smartlab.ui.theme.InputBGColor

@Composable
fun NewsCard(news: Actions) {
    Card(
        modifier = Modifier.size(280.dp, 150.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = InputBGColor)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCDE3FF))
            .padding(start = 16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start)
            {
                Text(
                    text = news.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.038.sp,
                    softWrap = true,
                    maxLines = 2,
                    fontFamily = FontFamily(Font(R.font.nunito_black))
                )

                Column{
                    Text(
                        text = news.description,
                        fontSize = if (news.description.length > 15) 12.sp else 14.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                        color = Color.White,
                        modifier = Modifier.padding(bottom = if (news.description.length > 15) 0.dp else 4.dp)
                    )
                    Text(
                        text = "${news.price} ₽",
                        color = Color.White,
                        fontSize = 20.sp,
                        lineHeight = 29.sp,
                        letterSpacing = 0.038.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_semibold))
                    )
                }
            }

            Image(
                painter =  painterResource(id = R.drawable.hello),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun NewsCardPrev() {
    NewsCard(news = Actions(1, "Anything", "Nothing", 1000, "R.drawable.shape"))
}