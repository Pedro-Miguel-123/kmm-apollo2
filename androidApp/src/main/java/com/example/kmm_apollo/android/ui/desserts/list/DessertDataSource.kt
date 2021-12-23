package com.example.kmm_apollo.android.ui.desserts.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
class DessertDataSource constructor(private val repository: DessertRepository): PagingSource<Int, Dessert>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dessert> {
        val pageNumber = params.key ?: 0

        return try {
            val response = repository.getDesserts(page = pageNumber, size = 10)
            val desserts = response?.results
            val prevKey = response?.info?.prev
            val nextKey = response?.info?.next
            LoadResult.Page(data = desserts ?: emptyList(), prevKey = prevKey, nextKey = nextKey)
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dessert>): Int? {
        TODO("Not yet implemented")
    }

}