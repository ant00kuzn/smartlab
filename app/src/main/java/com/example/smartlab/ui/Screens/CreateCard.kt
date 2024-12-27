package com.example.smartlab.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.isValidDate
import com.example.smartlab.code.isValidEmail
import com.example.smartlab.code.sha256
import com.example.smartlab.dataClasses.PatientCard
import com.example.smartlab.retrofit.sendCreateCard
import com.example.smartlab.ui.Components.CustomButton
import com.example.smartlab.ui.Components.PrimaryButton
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import com.example.smartlab.ui.theme.disabledButtonColor

@Composable
fun CreateCard(modifier: Modifier = Modifier, navController: NavController) {
    var textFirstName by remember { mutableStateOf("") }
    var textLastName by remember { mutableStateOf("") }
    var textThirdName by remember { mutableStateOf("") }
    var textBirthday by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var isErrorState by remember { mutableStateOf(false) }

    LaunchedEffect(textBirthday){
        isErrorState = !isValidDate(textBirthday)
    }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 28.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = "Создание карты пациента",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_black)),
                    lineHeight = 28.sp,
                    letterSpacing = 0.033.sp,
                    color = Color.Black
                ),
                softWrap = true,
                maxLines = 2,
                modifier = Modifier.weight(1.25f)
            )

            Text(
                text = "Пропустить",
                modifier = Modifier
                    .clickable { navController.navigate("homeScreen") }
                    .weight(0.75f)
                    .padding(top = 8.dp),
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Right,
                    fontFamily = FontFamily(Font(R.font.nunito))
                ),
                color = Color(0xFF1A6FEE)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Без карты пациента вы не сможете заказать анализы.",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF939396),
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold))
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            softWrap = true,
            maxLines = 2
        )
        Text(
            text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF939396),
                lineHeight = 20.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold))
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            softWrap = true,
            maxLines = 2
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = textFirstName,
            onValueChange = { textFirstName = it },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Имя",
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

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = textThirdName,
            onValueChange = { textThirdName = it },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Отчество",
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

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = textLastName,
            onValueChange = { textLastName = it },
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Фамилия",
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

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = textBirthday,
            onValueChange = { textBirthday = it },
            modifier = modifier.fillMaxWidth(),
            isError = isErrorState,
            placeholder = {
                Text(
                    text = "Дата рождения",
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

        Spacer(modifier = Modifier.height(24.dp))

        // Использование Box для Dropdown
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = gender,
                onValueChange = { gender = it },
                placeholder = { Text("Пол", fontWeight = FontWeight.Normal) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                        Modifier.clickable { expanded = !expanded }
                    )
                },
                readOnly = true,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = InputBGColor,
                    unfocusedContainerColor = InputBGColor,
                    focusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                    unfocusedPlaceholderColor = Color.Black.copy(alpha = 0.5f),
                    focusedBorderColor = InputFocusedBorderColor,
                    unfocusedBorderColor = InputStrokeColor,
                    cursorColor = AccentColor
                ),
            )


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                DropdownMenuItem(
                    text = { Text("мужской", fontWeight = FontWeight.SemiBold) },
                    onClick = {
                        gender = "мужской"
                        expanded = false
                    })

                DropdownMenuItem(
                    text = { Text("женский", fontWeight = FontWeight.SemiBold) },
                    onClick = {
                        gender = "женский"
                        expanded = false
                    })
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                //sendCreateCard(PatientCard((1..10000).random(), 1, textFirstName, textLastName, textThirdName, textBirthday, if(gender == "мужской") 1 else 2))
                navController.navigate("homeScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = textFirstName.isNotBlank() && textLastName.isNotBlank() && textBirthday.isNotBlank(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A6FEE),
                disabledContainerColor = disabledButtonColor,
                contentColor = Color.White,
                disabledContentColor = Color.White
            )
        ) {
            Text(
                text = "Создать",
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.nunito_semibold)),
                lineHeight = 24.sp
            )
        }
    }
}

@Preview
@Composable
private fun CrateCardPrev() {
    CreateCard(navController = rememberNavController())
}