package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.*
import com.example.mycity.ui.CategoryScreen
import com.example.mycity.ui.DetailScreen
import com.example.mycity.ui.PlaceListScreen
import com.example.mycity.ui.TopBar
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.mycity.Database.DataSource


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MyCityApp(windowSizeClass)
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(windowSizeClass: WindowSizeClass){
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val widthClass = windowSizeClass.widthSizeClass
    when (widthClass){
        WindowWidthSizeClass.Compact -> {
           Scaffold(
                topBar = {
                    TopBar(
                        navController = navController,
                        currentRoute = currentRoute,
                        backStackEntry = currentBackStackEntry
                    )
                }
                ) { innerPadding ->

                NavHost(
                    navController = navController,
                    startDestination = "categories",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("categories") {
                        CategoryScreen(onCategoryClick = { category ->
                                navController.navigate("places/${category}")
                            })
                    }
                    composable("places/{category}") { backStackEntry ->
                        val category = backStackEntry.arguments?.getString("category")
                        PlaceListScreen(
                            category = category,
                            onPlaceClick = { name, description, image ->
                                navController.navigate("detail/$name/$description/$image")
                            },
                            true
                        )
                    }
                    composable(
                        route = "detail/{name}/{description}/{image}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType },
                            navArgument("description") { type = NavType.StringType },
                            navArgument("image") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name")
                        val description = backStackEntry.arguments?.getString("description")
                        val image = backStackEntry.arguments?.getInt("image")
                        DetailScreen(name, image ?: 0, description)
                    }
            }
        }
        }
        else -> {
            var selectedCategory by remember { mutableStateOf("restaurants") }
            var selectedName by remember { mutableStateOf<String?>(DataSource.restaurants.first().name) }
            var selectedDescription by remember { mutableStateOf<String?>(DataSource.restaurants.first().description) }
            var selectedImage by remember { mutableStateOf(DataSource.restaurants.first().imageRes) }

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("My City") })
                }
            ) { innerPadding ->
                Row(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

                    // LEFT — Categories (1/4)
                    Column(modifier = Modifier.weight(1f)) {
                        CategoryScreen(onCategoryClick = { category ->
                            selectedCategory = category
                            val firstPlace = when (category) {
                                "restaurants" -> DataSource.restaurants.first()
                                "parks" -> DataSource.parks.first()
                                "museums" -> DataSource.museums.first()
                                else -> null
                            }
                            selectedName = firstPlace?.name
                            selectedDescription = firstPlace?.description
                            selectedImage = firstPlace?.imageRes ?: 0
                        })
                    }

                    // MIDDLE — Places (1/4)
                    Column(modifier = Modifier.weight(1f)) {
                        if (selectedCategory != null) {
                            PlaceListScreen(
                                category = selectedCategory,
                                onPlaceClick = { name, description, image ->
                                    selectedName = name
                                    selectedDescription = description
                                    selectedImage = image
                                },
                                false
                            )
                        } else {
                                Text("Select a category", style = MaterialTheme.typography.bodyLarge)

                        }
                    }

                    // RIGHT — Detail (2/4)
                    Column(modifier = Modifier.weight(2f)) {
                        if (selectedName != null) {
                            DetailScreen(selectedName, selectedImage, selectedDescription)
                        } else {
                            Text("Select a place to see details")
                        }
                    }
                }
            }
        }
    }
}
