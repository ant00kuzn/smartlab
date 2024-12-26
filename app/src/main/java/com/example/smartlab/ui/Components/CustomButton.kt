package com.example.smartlab.ui.Components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R

@Composable
fun CustomButton(number: Int?, onNumberClick: (Int) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    var buttonColor by remember { mutableStateOf(Color(0xFFF5F5F9)) }

    val animatedButtonColor by animateColorAsState(
        targetValue = buttonColor,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )

    Button(
        onClick = {
            if (number != null) {
                onNumberClick(number)
            } else {
                onNumberClick(-1)
            }
        },
        modifier = Modifier.size(80.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedButtonColor,
            contentColor = Color.Black
        ),
        interactionSource = interactionSource
    ) {
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        buttonColor = Color(0xFF1A6FEE)
                    }

                    is PressInteraction.Release -> {
                        buttonColor = Color(0xFFF5F5F9)
                    }

                    is PressInteraction.Cancel -> {
                        buttonColor = Color(0xFFF5F5F9)
                    }
                }
            }
        }

        if (number != null) {
            Text(
                text = number.toString(),
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.033.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    color = Color.Black
                )
            )
        }
    }
}

@Preview
@Composable
private fun CustomButtonPrev() {
    CustomButton(
        0,
        onNumberClick = {}
    )
}