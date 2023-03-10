package com.example.kmm_apollo.android.ui.desserts.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.cache.ActionType
import com.example.kmmapollo.shared.cache.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import org.koin.androidx.compose.getViewModel

@ExperimentalCoroutinesApi
@ApolloExperimental
@Composable
fun ReviewFormView(reviewId: String, dessertId: String, action: ActionType, popBack: () -> Unit) {
    val reviewFormViewModel = getViewModel<ReviewFormViewModel>()
    val (review, setReview) = remember { mutableStateOf(Review("", "", "", "", 0)) }
    val isEditing = action != ActionType.CREATE
    val scope = rememberCoroutineScope()
    val label = if (isEditing) "Save" else "Create"

    LaunchedEffect(reviewId) {
        try {
            if (isEditing) {
                val readReview = reviewFormViewModel.getReview(reviewId)
                readReview?.let {
                    setReview(it)
                }
            }
        } catch(err: Exception) {
            print(err.message)
        }
    }

    suspend fun handleReview(action: ActionType) {
        when (action) {
            ActionType.CREATE -> {
                reviewFormViewModel.createReview(dessertId, review)
                popBack()
            }
            ActionType.UPDATE -> {
                val updateDessert = reviewFormViewModel.updateReview(review)
                updateDessert?.let {
                    setReview(it)
                }
                popBack()
            }
            ActionType.DELETE -> {
                val deleted = reviewFormViewModel.deleteReview(review.id)
                if (deleted == true) popBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$label Review") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack)
                    }
                }
            )
        }) {
            review?.let {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Description",
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.body1
                        )
                        TextField(
                            value = review.text,
                            onValueChange = { setReview(review.copy(text = it)) }

                        )
                        Text(
                            text = "Rating",
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.body1
                        )

                        Row {
                            List(5) {
                                val rating = it + 1
                                if (rating <= review.rating.toInt()) {
                                    IconButton(onClick = {
                                        setReview(review.copy(rating = rating.toLong()))
                                    }) {
                                        Icon(Icons.Filled.Star, tint = MaterialTheme.colors.primary)
                                    }
                                } else {
                                    IconButton(onClick = {
                                        setReview(review.copy(rating = rating.toLong()))
                                    }) {
                                        Icon(Icons.Filled.Star, tint = Color(0xFFd3d3d3))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                scope.async {
                                    handleReview(action)
                                }
                            }, modifier = Modifier
                                .padding(16.dp)
                                .width(320.dp)
                        ) {
                            Text(label)
                        }

                        if (isEditing) {
                            Button(
                                onClick = {
                                    scope.async {
                                        handleReview(ActionType.DELETE)
                                    }
                                }, modifier = Modifier
                                    .padding(16.dp)
                                    .width(320.dp)
                            ) {
                                Text("Delete")
                            }
                        }

                    }
                }
            }
        }
}