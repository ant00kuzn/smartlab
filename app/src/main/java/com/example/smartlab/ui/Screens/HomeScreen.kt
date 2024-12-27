package com.example.smartlab.ui.Screens

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.code.getAct
import com.example.smartlab.code.getCat
import com.example.smartlab.code.getProd
import com.example.smartlab.code.getProductsByCategory
import com.example.smartlab.dataClasses.Actions
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.dataClasses.Products
import com.example.smartlab.ui.Components.BottomNavigationBar
import com.example.smartlab.ui.Components.CatalogSection
import com.example.smartlab.ui.Components.NewsCard
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(navController: NavController, context: Context) {
    var textState by remember { mutableStateOf("") }
    var isNewsVisible by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    var actionsList by remember { mutableStateOf<List<Actions>>(emptyList()) }
    var productsList by remember { mutableStateOf<List<Products>>(emptyList()) }
    var categoriesList by remember { mutableStateOf<List<Categories>>(emptyList()) }
    var selectedCategory by remember { mutableStateOf<Categories?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val preferencesManager = remember { PreferencesManager(context) }
    val textSt = remember { mutableStateOf(preferencesManager.getData("textState", "")) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            actionsList = getAct().value
            val fetchedCategories = getCat().value
            categoriesList = fetchedCategories
            productsList = getProd().value

            // Select first category
            if (fetchedCategories.isNotEmpty()){
                selectedCategory = fetchedCategories.first()
                productsList = getProductsByCategory(fetchedCategories.first().id).value
            }

        }
    }

    suspend fun onCategoryClick(category: Categories){
        selectedCategory = category
        productsList = getProductsByCategory(category.id).value
    }

    Scaffold(
        modifier = Modifier.nestedScroll(rememberNestedScrollInteropConnection()),
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
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
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                    ,
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
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
                        items(actionsList) { actions ->
                            NewsCard(actions)
                        }
                    }
                }
            }

            // Блок "Каталог анализов"
            CatalogSection(
                categoriesList = categoriesList,
                productsList = productsList,
                selectedCategory = selectedCategory,
                onCategoryClick = {
                    coroutineScope.launch {
                        onCategoryClick(it)
                    }
                }
            )
        }
    }
    LaunchedEffect(scrollState.value) {
        isNewsVisible = scrollState.value <= 100
    }
}


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(navController = rememberNavController(), context = LocalContext.current)
}