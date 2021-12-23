package com.example.kmm_apollo.android.ui.desserts.favorites

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
class FavoriteDataSource constructor(private val repository: DessertRepository): PagingSource<Int, Dessert>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dessert> {
        val favorites = repository.getFavoriteDesserts()
        return LoadResult.Page(data = favorites, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, Dessert>): Int? {
        TODO("Not yet implemented")
    }
}