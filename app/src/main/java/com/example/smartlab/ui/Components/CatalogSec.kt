package com.example.smartlab.ui.Components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.R
import com.example.smartlab.dataClasses.Categories
import com.example.smartlab.dataClasses.Products
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun CatalogSection(
    categoriesList: List<Categories>,
    productsList: List<Products>,
    selectedCategory: Categories? = null,
    onCategoryClick: suspend (Categories) -> Unit
) {
    var coroutine = rememberCoroutineScope()
    var expandedHeight by remember { mutableStateOf(150.dp) } // Изначальная высота блока
    val density = LocalDensity.current
    val maxExpandedHeight =  density.run{ 500.dp.toPx()} // Максимальная высота всего блока
    val minExpandedHeight = density.run { 150.dp.toPx() }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newHeight = (expandedHeight.value - delta).coerceIn(minExpandedHeight, maxExpandedHeight).dp

                if (newHeight != expandedHeight)
                {
                    expandedHeight = newHeight
                    return Offset.Zero
                }

                return available;
            }
        }
    }
    val animatedHeight by animateFloatAsState(
        targetValue = expandedHeight.value,
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedHeight.dp)
            .nestedScroll(nestedScrollConnection)
    ) {
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
            items(categoriesList) { category ->
                CategoryButton(text = category.name, onClick = {
                    coroutine.launch{onCategoryClick(category)}
                }, isSelected = selectedCategory == category)

            }
        }

        // Список услуг
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(productsList) { product ->
                ProductCard(
                    modifier = Modifier.padding(16.dp),
                    product = product
                )
            }
        }
    }
}