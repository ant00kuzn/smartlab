package com.example.smartlab.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.code.RetrofitHelper.usersInterface
import com.example.smartlab.ui.Components.SearchResultItem
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreenResults() {
    var textState by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf(listOf<String>()) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(textState) {
        if (textState.length >= 3) {
            isLoading = true
            coroutineScope.launch {
                try {
                    //searchResults = getProd(textState)
                    delay(1000)
                } catch (e: Exception) {
                    // Обработка ошибок
                    searchResults = emptyList()
                    println("error : " + e.message)
                } finally {
                    isLoading = false
                }
            }
        } else {
            searchResults = emptyList()
            isLoading = false
        }
    }

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Поисковая строка
            OutlinedTextField(
                value = textState,
                onValueChange = { textState = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text(
                        text = "Искать анализы",
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
                    cursorColor = AccentColor
                ),
                shape = RoundedCornerShape(10.dp)
            )

            // Результаты поиска
            if (isLoading){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            } else if (searchResults.isNotEmpty()){
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    items(searchResults) { result ->
                        SearchResultItem(text = result)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Введите запрос для поиска")
                }
            }
        }
    }
}