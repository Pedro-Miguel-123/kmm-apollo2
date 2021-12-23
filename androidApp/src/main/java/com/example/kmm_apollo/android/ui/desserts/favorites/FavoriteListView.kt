package com.example.kmm_apollo.android.ui.desserts.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.items
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.android.ui.desserts.list.DessertListRowView
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.getViewModel


@ExperimentalCoilApi
@ApolloExperimental
@ExperimentalCoroutinesApi
@Composable
fun FavoriteListView(bottomBar: @Composable () -> Unit, dessertSelected: (dessert: Dessert) -> Unit) {
    val favoriteListViewModel = getViewModel<FavoriteListViewModel>()
    val lazyDessertList = favoriteListViewModel.desserts.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text( "Favorites") }) },
        bottomBar = bottomBar) {
            LazyColumn(contentPadding = it) {
                items(lazyDessertList) { favorite ->
                    favorite?.let { dessert ->
                        DessertListRowView(dessert, dessertSelected)
                    }
                }
            }
        }
}