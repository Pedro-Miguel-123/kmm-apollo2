package com.example.kmm_apollo.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.interceptor.BearerTokenInterceptor
import com.apollographql.apollo.interceptor.TokenProvider
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import com.example.kmm_apollo.shared.cache.Database
import com.example.kmm_apollo.shared.cache.DatabaseDriverFactory
import com.example.kmm_apollo.shared.logger.LoggingInterceptor
import com.example.kmm_apollo.shared.logger.MyLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ApolloProvider(databaseDriverFactory: DatabaseDriverFactory) : TokenProvider {

    internal val database = Database(databaseDriverFactory)

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    internal val apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://ktor-graphql-server2.herokuapp.com/graphql",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json",
            ),
        ),
        interceptors = listOf(BearerTokenInterceptor(this))
    )

    override suspend fun currentToken(): String {
        return database.getUserState()?.token ?: ""
    }

    override suspend fun refreshToken(previousToken: String): String {
        return ""
    }
}