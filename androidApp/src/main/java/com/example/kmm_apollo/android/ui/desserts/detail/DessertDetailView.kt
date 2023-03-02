package com.example.kmm_apollo.android.ui.desserts.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.android.ui.desserts.review.ReviewListView
import com.example.kmmapollo.shared.cache.Dessert
import com.example.kmmapollo.shared.cache.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ApolloExperimental
@Composable
fun DessertDetailView(dessertId: String,
                      editDessertSelected: (dessertId: String) -> Unit,
                      editReviewSelected: (reviewId: String) -> Unit,
                      createReviewSelected: (dessertId: String) -> Unit,
                      popBack: () -> Unit) {
    val dessertDetailViewModel = getViewModel<DessertDetailViewModel>()
    val (dessert, setDessert) = remember { mutableStateOf(Dessert("", "", "", "", "")) }
    val (reviews, setReviews) = remember { mutableStateOf(emptyList<Review>()) }
    val (isFavorite, setIsFavorite) = remember { mutableStateOf(false) }
    val (userId, setUserId) = remember { mutableStateOf("") }

    LaunchedEffect(dessertId) {
        val favDessert = dessertDetailViewModel.getFavorite(dessertId)
        val isFavoriteDessert = favDessert != null
        setIsFavorite(isFavoriteDessert)
        setUserId(dessertDetailViewModel.getUserState()?.userId ?: "")
        try {
            // Read Dessert from local database
            if (isFavoriteDessert) {
                setDessert(favDessert!!)
            }
            val readDessert = dessertDetailViewModel.getDessert(dessertId)
            readDessert?.let {
                setDessert(it.dessert)
                setReviews(it.reviews)
                // Update Favorite Dessert
                if (isFavoriteDessert) {
                    dessertDetailViewModel.updateFavorite(dessert)
                }
            } ?: run {
                popBack()
            }
        } catch(err: Exception) {
            print(err.message)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(dessert.name) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Toggle Favorite
                        if (isFavorite) {
                            dessertDetailViewModel.removeFavorite(dessertId)
                        } else {
                            dessertDetailViewModel.saveFavorite(dessert)
                        }
                        setIsFavorite(!isFavorite)
                    }) {
                        if (isFavorite) {
                            Icon(Icons.Outlined.Delete)
                        } else {
                            Icon(Icons.Filled.Favorite)
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (userId == dessert.userId) {
                FloatingActionButton(onClick = {
                    editDessertSelected(dessertId)
                }, backgroundColor = MaterialTheme.colors.primary) {
                    Icon(Icons.Outlined.Create)
                }
            }
        }) {
            Surface(color = Color.White) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    Surface(color = Color.White) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            val imageUrl = dessert.imageUrl
                            Card(
                                modifier = Modifier.size(150.dp),
                                shape = RoundedCornerShape(25.dp)
                            ) {
                                CoilImage(data = imageUrl, contentScale = ContentScale.Crop)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Summary", style = typography.h5, color = MaterialTheme.colors.primary,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )

                    Text(
                        dessert.description, style = typography.body1,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            "Reviews", style = typography.h5, color = MaterialTheme.colors.primary,
                        )
                        if (userId.isNotEmpty()) {
                            Button(onClick = {
                                createReviewSelected(dessertId)
                            }) {
                                Icon(Icons.Outlined.Add)
                            }
                        }
                    }

                    Surface(color = Color.White) {
                        ReviewListView(
                            reviews,
                            userId,
                            editReviewSelected = {
                                editReviewSelected(it.id)
                            })
                    }
                }
            }
        }
}