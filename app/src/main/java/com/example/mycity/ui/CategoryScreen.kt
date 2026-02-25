package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.Database.Category
import com.example.mycity.R

@Composable
fun CategoryScreen(onCategoryClick: (String) -> Unit){
//    val categories = listOf(
//        "Restaurants",
//        "Parks",
//        "Museums"
//    )
    val categories = listOf(
        Category("Restaurants", R.drawable.restaurants),
        Category("Parks", R.drawable.parks),
        Category("Museums", R.drawable.museums)
    )

    LazyColumn(modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

        items(categories){
            category ->

            Card(modifier = Modifier.fillMaxSize(),
                onClick = { onCategoryClick(category.name.lowercase()) })
            {
                Column(modifier = Modifier,
                    verticalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(id = category.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp))

//                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}