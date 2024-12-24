package com.example.smartlab.ui.Screens

import android.graphics.Bitmap
import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.ui.components.OnboardDescription
import com.example.smartlab.ui.components.OnboardHeader
import com.example.smartlab.ui.components.TextButton

@Composable
fun OnBoard(modifier: Modifier = Modifier, navController: NavController, buttonText: String, onBoardHeaderText: String, onBoardDescriptionText: String, dot: Int, onBoard: Int) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth()){
            TextButton(
                text = buttonText,
                modifier = Modifier.weight(0.5f).padding(top = 49.dp, start = 30.dp),
                onClick = { navController.navigate("auth") }
            )

            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.kub),
                contentDescription = null,
                modifier = Modifier.weight(0.5f).size(167.dp)
            )
        }

        Spacer(modifier = Modifier.height(60.9.dp))

        OnboardHeader(
            modifier = Modifier,
            text = onBoardHeaderText
        )

        Spacer(modifier = Modifier.height(29.dp))

        OnboardDescription(
            modifier = Modifier,
            text = onBoardDescriptionText
        )

        Spacer(modifier = Modifier.height(60.dp))

        Image(
            bitmap = ImageBitmap.imageResource(dot),
            contentDescription = null,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(113.4.dp))

        Image(
            bitmap = ImageBitmap.imageResource(onBoard),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Preview
@Composable
private fun OnBoardPreview() {
    OnBoard(
        buttonText = "Пропустить",
        onBoardHeaderText="Анализы",
        onBoardDescriptionText = "Экспресс сбор и получение проб",
        dot = R.drawable.dot1,
        onBoard = R.drawable.onboard2,
        navController = rememberNavController()
    )
}