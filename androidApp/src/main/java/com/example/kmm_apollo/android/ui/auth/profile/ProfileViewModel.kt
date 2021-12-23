package com.example.kmm_apollo.android.ui.auth.profile

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.AuthRepository
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class ProfileViewModel constructor(private val repository: AuthRepository) : ViewModel() {
    @ExperimentalCoroutinesApi
    @ApolloExperimental
    val desserts: Flow<PagingData<Dessert>> = Pager(PagingConfig(pageSize = 100)) {
        ProfileDataSource(repository)
    }.flow
}