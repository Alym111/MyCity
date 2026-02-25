package com.example.mycity.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController, currentRoute: String?, backStackEntry: NavBackStackEntry?){
    val title = when {
        currentRoute == "categories" -> "My City"
        currentRoute?.startsWith("places") == true -> {
            backStackEntry?.arguments?.getString("category") ?: ""

        }
        currentRoute?.startsWith("detail") == true -> {
            backStackEntry?.arguments?.getString("name") ?: ""
        }

        else -> "My City"
    }
    TopAppBar(
        title = {Text(title)},
        navigationIcon = {
            if (currentRoute != "categories"){
                IconButton(onClick = {navController.popBackStack()}){
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}