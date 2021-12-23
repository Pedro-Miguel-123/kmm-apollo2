package com.example.kmm_apollo.android.ui.desserts.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class DessertListViewModel constructor(private val repository: DessertRepository): ViewModel() {
    @ExperimentalCoroutinesApi
    @ApolloExperimental
    val desserts: Flow<PagingData<Dessert>> = Pager(PagingConfig(pageSize = 10)) {
        DessertDataSource(repository)
    }.flow
}