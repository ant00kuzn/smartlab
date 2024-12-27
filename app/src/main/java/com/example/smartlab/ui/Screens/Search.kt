package com.example.smartlab.ui.Screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.R
import com.example.smartlab.code.PreferencesManager
import com.example.smartlab.retrofit.getProdSear
import com.example.smartlab.dataClasses.Products
import com.example.smartlab.retrofit.getAct
import com.example.smartlab.retrofit.getCat
import com.example.smartlab.retrofit.getProd
import com.example.smartlab.ui.Components.ClearIcon
import com.example.smartlab.ui.Components.ProductCard
import com.example.smartlab.ui.theme.AccentColor
import com.example.smartlab.ui.theme.InputBGColor
import com.example.smartlab.ui.theme.InputFocusedBorderColor
import com.example.smartlab.ui.theme.InputStrokeColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SearchScreen(modifier: Modifier = Modifier, navController: NavController, context: Context) {
    val preferencesManager = remember { PreferencesManager(context) }
    val textState = remember { mutableStateOf(preferencesManager.getData("textState", "")) }
    var productsList by remember { mutableStateOf<List<Products>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf(textState.value) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            val fetchedProducts = getProd().value

            withContext(Dispatchers.Main) {
                productsList = fetchedProducts
            }
        }
    }

    Column(modifier = Modifier
        .background(Color.White)
        .padding(horizontal = 20.dp, vertical = 16.dp)
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it

                    if (searchText.length >= 3) {
                        coroutineScope.launch {
                            productsList = productsList.filter { product ->
                                product.name.lowercase().contains(searchText.lowercase()) //filter
                            }
                        }
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable {
                            searchText = ""
                        }
                    )
                },
                modifier = Modifier
                    .border(1.dp, Color(0xFFEBEBEB))
                    .weight(1.5f)
                    .fillMaxWidth(),
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
                trailingIcon = { ClearIcon() }
            )

            Text(
                text = "Отменить",
                modifier = with(Modifier.align(Alignment.CenterVertically)) {
                    clickable { navController.popBackStack() }
                        .weight(0.5f)
                },
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Right,
                    fontFamily = FontFamily(Font(R.font.nunito))
                ),
                color = Color(0xFF1A6FEE)
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFF4F4F4)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn() {
            items(productsList) { product ->
                ProductCard(
                    modifier = Modifier.padding(16.dp),
                    product = product
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPrev() {
    SearchScreen(navController = rememberNavController(), context = LocalContext.current)
}