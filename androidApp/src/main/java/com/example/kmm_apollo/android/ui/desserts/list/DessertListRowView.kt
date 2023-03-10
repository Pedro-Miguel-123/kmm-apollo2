package com.example.kmm_apollo.android.ui.desserts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import com.example.kmmapollo.shared.cache.Dessert

@Composable
fun DessertListRowView(dessert: Dessert, dessertSelected: (dessert: Dessert) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = { dessertSelected(dessert) })
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(modifier = Modifier.size(50.dp), shape = CircleShape) {
            CoilImage(data = dessert.imageUrl, contentScale = ContentScale.Crop)
        }

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(dessert.name, style = MaterialTheme.typography.h6)
            Text(dessert.description,
                style = MaterialTheme.typography.subtitle2, color = Color.Gray)
        }
    }
    Divider()
}
