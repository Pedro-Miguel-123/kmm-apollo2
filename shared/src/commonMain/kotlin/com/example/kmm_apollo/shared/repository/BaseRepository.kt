package com.example.kmm_apollo.shared.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.ApolloProvider
import com.example.kmm_apollo.shared.cache.Database
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
open class BaseRepository(apolloProvider: ApolloProvider) {
    val apolloClient: ApolloClient = apolloProvider.apolloClient
    val database: Database = apolloProvider.database
}