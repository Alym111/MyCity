package com.example.mycity.ui

import android.provider.ContactsContract
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.Database.DataSource

@Composable
fun PlaceListScreen(category: String?, onPlaceClick: (String, String, Int) -> Unit, isPhone: Boolean){

    val places = when (category){
        "restaurants" -> DataSource.restaurants
        "parks" -> DataSource.parks
        "museums" -> DataSource.museums
        else -> emptyList()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(places) { place ->

            Card(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                onPlaceClick(place.name, place.description, place.imageRes)
            })
            {
                if(isPhone){
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(painter = painterResource(id = place.imageRes),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp))

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = place.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                else{
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(painter = painterResource(id = place.imageRes),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp))

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = place.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

            }
        }


//        places.forEach { place ->
//            Button(onClick = {navController.navigate("detail/${place.name}/${place.description}")}){
//                Text(place.name)
//                }
//
//            }
//        }
        }
    }