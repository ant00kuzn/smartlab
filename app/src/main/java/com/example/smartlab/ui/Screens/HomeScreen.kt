package com.example.smartlab.ui.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.code.RetrofitHelper
import com.example.smartlab.code.getAct
import com.example.smartlab.dataClasses.Actions
import com.example.smartlab.dataClasses.ServiceItem
import com.example.smartlab.ui.Components.CategoryButton
import com.example.smartlab.ui.Components.NewsCard
import com.example.smartlab.ui.Components.ServiceCard

@Composable
fun HomeScreen(navController: NavController) {
    val textState = remember { mutableStateOf("") }
    var isNewsVisible by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

//    var actionsList = remember { mutableStateListOf<Actions>() }

    Scaffold(modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection())) { innerPadding ->
//        actionsList = getAct()
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // Поисковая строка
                OutlinedTextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { navController.navigate("searchStart") },
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
                    shape = RoundedCornerShape(10.dp),
                    enabled = false
                )

                // Блок "Акции и новости"
                AnimatedVisibility(
                    visible = isNewsVisible,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    Column() {
                        Text(
                            text = "Акции и новости",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.nunito_bold))
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
//                            actionsList.forEach(actions ->
//
//                            )
                        }
                    }
                }
                LaunchedEffect(scrollState.value) {
                    isNewsVisible = scrollState.value <= 100
                }
                // Блок "Каталог анализов"
                Text(
                    text = "Каталог анализов",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
//                    items(categories) { category ->
//                        CategoryButton(text = category)
//                    }
                }

                // Список услуг
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
//                    items(services) { service ->
//                        ServiceCard(
//                            modifier = Modifier.padding(16.dp),
//                            service = service
//                        )
//                    }
                }
            }

    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController())
}