package com.example.smartlab.ui.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.retrofit.getAct
import com.example.smartlab.retrofit.getCat
import com.example.smartlab.retrofit.getProd
import com.example.smartlab.dataClasses.Actions
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.dataClasses.Products
import com.example.smartlab.ui.Components.BottomNavigationBar
import com.example.smartlab.ui.Components.CategoryButton
import com.example.smartlab.ui.Components.NewsCard
import com.example.smartlab.ui.Components.ProductCard
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun HomeScreen(navController: NavController, context: Context) {
    var textState by remember { mutableStateOf("") }
    var isNewsVisible by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    var actionsList by remember { mutableStateOf<List<Actions>>(emptyList()) }
    var allProductsList by remember { mutableStateOf<List<Products>>(emptyList()) } // All products
    var productsList by remember { mutableStateOf<List<Products>>(emptyList()) } // Filtered products
    var categoriesList by remember { mutableStateOf<List<Categories>>(emptyList()) }
    var selectedCategory by remember { mutableStateOf<Categories?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val preferencesManager = remember { PreferencesManager(context) }
    val txtState = remember { mutableStateOf(preferencesManager.getData("textState", "")) }

    var expandedHeight by remember { mutableStateOf(500.dp) }
    val animatedHeight by animateFloatAsState(
        targetValue = expandedHeight.value,
        label = ""
    )


    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) { // Выполняем загрузку в IO-потоке
            val fetchedActions = getAct().value
            val fetchedCategories = getCat().value
            val fetchedProducts = getProd().value

            withContext(Dispatchers.Main) { // Возвращаемся в UI-поток для обновления состояния
                actionsList = fetchedActions
                categoriesList = fetchedCategories
                allProductsList = fetchedProducts
                productsList = fetchedProducts // initially, show all products

                // Select first category
                if (categoriesList.isNotEmpty()) {
                    selectedCategory = categoriesList.first()
                    productsList = allProductsList.filter { it.category_id == selectedCategory?.id }
                }
            }
        }
    }

    fun onCategoryClick(category: Categories) {
        selectedCategory = category
        productsList = allProductsList.filter { it.category_id == category.id }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection()),
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                // Поисковая строка
                OutlinedTextField(
                    value = textState,
                    onValueChange = {
                        textState = it
                        preferencesManager.saveData("textState", textState)
                        txtState.value = textState
                    },
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
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        val scrollState = rememberLazyListState()

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Блок "Акции и новости"
            AnimatedVisibility(
                visible = scrollState.firstVisibleItemIndex == 0,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Акции и новости",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = Color(0xFF939396)
                    )
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(actionsList) { actions ->
                            NewsCard(actions)
                        }
                    }

                    Text(
                        text = "Каталог анализов",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        color = Color(0xFF939396)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Блок "Каталог анализов"
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animatedHeight.dp),
            ) {
                // Блок "Каталог анализов"
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categoriesList) { category ->
                        CategoryButton(
                            text = category.name,
                            onClick = {
                                onCategoryClick(category)
                            },
                            isSelected = selectedCategory == category
                        )
                    }
                }

                // Список услуг
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (scrollState.isScrollInProgress) expandedHeight = 1000.dp
                    items(productsList) { product ->
                        ProductCard(
                            modifier = Modifier.padding(16.dp),
                            product = product
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(scrollState.value) {
        isNewsVisible = scrollState.value <= 100
    }
    LaunchedEffect(textState) {
        if (textState.isNotBlank()) {
            navController.navigate("startSearch")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController(), context = LocalContext.current)
}