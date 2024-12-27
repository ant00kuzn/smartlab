package com.example.smartlab.ui.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.smartlab.dataClasses.NavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Анализы", "homeScreen", Icons.AutoMirrored.Filled.List),
        NavigationItem("Результаты", "", Icons.Filled.Settings),
        NavigationItem("Поддержка", "", Icons.Filled.Phone),
        NavigationItem("Профиль", "ProfileScreen", Icons.Filled.Person)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}