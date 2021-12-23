package com.example.kmm_apollo.android.ui.auth.profile

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.AuthRepository
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
class ProfileDataSource constructor(private val repository: AuthRepository): PagingSource<Int, Dessert>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dessert> {
        return try {
            val favorites = repository.getProfileDesserts()
            LoadResult.Page(data = favorites, prevKey = null, nextKey = null)
        } catch(err: Exception) {
            LoadResult.Error(err)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Dessert>): Int? {
        TODO("Not yet implemented")
    }
}