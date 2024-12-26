package com.example.smartlab.ui.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.isValidEmail
import com.example.smartlab.code.sendCode
import com.example.smartlab.code.sha256
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.code.checkAuth
import com.example.smartlab.code.setUser
import com.example.smartlab.ui.Components.PrimaryButton
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor

@SuppressLint("RememberReturnType")
@Composable
fun Authorization(modifier: Modifier = Modifier, textDescription: String, textUnderBut: String, textLink: String, navController: NavController, context: Context) {
    var textPassword by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }

    val preferencesManager = remember { PreferencesManager(context) }
    val data = remember { mutableStateOf(preferencesManager.getData("isCompleted", false.toString())) }
    val email = remember { mutableStateOf(preferencesManager.getData("email", "")) }
    val passHash = remember { mutableStateOf(preferencesManager.getData("password", "")) }

    var passwordVisible by remember { mutableStateOf(false) }
    val icon = if (passwordVisible) R.drawable.eye_open else R.drawable.eye_closed

    var isErrorState by remember { mutableStateOf(false) }

    LaunchedEffect(textEmail){
        isErrorState = !isValidEmail(textEmail)
    }

    val trailingIcon = @Composable {
        Icon(
            painter = BitmapPainter(ImageBitmap.imageResource(id = icon)),
            contentDescription = null,
            modifier = Modifier.size(14.dp).clickable {
                passwordVisible = !passwordVisible
            }
        )
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.height(103.dp))
        Row(){
            Image(bitmap = ImageBitmap.imageResource(R.drawable.hello), "hello image", modifier = Modifier.size(32.dp))

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Добро пожаловать!",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.nunito_black)),
                lineHeight = 28.sp,
                letterSpacing = 0.033.sp,
                color = Color(0xFF000000)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = textDescription,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            lineHeight = 20.sp,
            color = Color.Black,
            softWrap = true,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text= "E-mail",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            lineHeight = 14.sp,
            color = Color(0xFF7E7E9A)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            isError = isErrorState,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "example@mail.ru",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                    lineHeight = 20.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = InputBGColor,
                unfocusedContainerColor = InputBGColor,
                focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                focusedBorderColor = InputFocusedBorderColor,
                unfocusedBorderColor = InputStrokeColor,
                cursorColor = AccentColor,
                focusedTextColor = Color(0xFF000000),
                unfocusedTextColor = Color(0xF0000000),
                errorTextColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text= "Password",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_semibold)),
            lineHeight = 14.sp,
            color = Color(0xFF7E7E9A)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = textPassword,
            onValueChange = { textPassword = it },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "*********",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = InputBGColor,
                unfocusedContainerColor = InputBGColor,
                focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                focusedBorderColor = InputFocusedBorderColor,
                unfocusedBorderColor = InputStrokeColor,
                cursorColor = AccentColor,
                focusedTrailingIconColor = Color.Black,
                unfocusedTrailingIconColor = Color.Black,
                focusedTextColor = Color(0xFF000000),
                unfocusedTextColor = Color(0xF0000000)
            ),
            shape = RoundedCornerShape(10.dp),
            trailingIcon = trailingIcon,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            onClick = {
                if (textLink == "Войти") {
                    setUser(textEmail, sha256(textPassword))
                    Log.v("set us", "${textEmail} ${sha256(textPassword)}")
                    sendCode(textEmail)
                    navController.navigate("getCode")
                }else{
                    checkAuth(textEmail, sha256(textPassword))
                    Log.v("login", "${textEmail} ${sha256(textPassword)}")
                    navController.navigate("createPassword")
                }
            },
            text = "Далее",
            enabled = if (textEmail != "" && textPassword.length >= 6) true else false
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().height(25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = textUnderBut,
                modifier = Modifier,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                lineHeight = 20.sp,
                textAlign = TextAlign.End,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = textLink,
                modifier = modifier
                    .clickable {
                        if (textLink == "Войти"){
                            navController.navigate("auth")
                        } else {
                            navController.navigate("reg")
                        }
                    },
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = Color(0xFF1A6FEE)
            )
        }
    }

    preferencesManager.saveData("isCompleted", true.toString())
    preferencesManager.saveData("email", textEmail)
    preferencesManager.saveData("password", sha256(textPassword))
    data.value = true.toString()
    email.value = textEmail
    passHash.value = textPassword
}

@Preview
@Composable
private fun AuthorizationPreview() {
    Authorization(
        modifier = Modifier,
        textDescription = "Зарегистрируйтесь, чтобы пользоваться функциями приложения",
        textUnderBut = "Уже есть аккаунт?",
        textLink = "Войти",
        navController = rememberNavController(),
        context = LocalContext.current
    )
}