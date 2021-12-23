package com.example.kmm_apollo.shared.repository

import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.*
import com.example.kmm_apollo.shared.ApolloProvider
import com.example.kmm_apollo.shared.cache.*
import com.example.kmm_apollo.type.DessertInput
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

// 1 : Create a DessertRepository class with a dependency on an apolloProvider.
// The DessertRepository implements the open class BaseRepository.
// By implementing this class, we get access to the apolloClient and database instances.
class DessertRepository(apolloProvider: ApolloProvider): BaseRepository(apolloProvider) {
    // 2 : Write a coroutine function called getDesserts,
    // which fetches the paginated list of desserts and returns a Desserts response object.
    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getDesserts(page: Int, size: Int): Desserts? {
        // 3 : For the response object, we can send a generated query called GetDessertsQuery, and execute it on the apollo client.
        val response = apolloClient.query(GetDessertsQuery(page, size)).execute().single()
        // 4 : Return the Desserts response object from the GraphQL server, and map it to our client-side models using toDesserts().
        return response.data?.desserts?.toDesserts()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getDessert(dessertId: String): DessertDetail? {
        val response = apolloClient.query(GetDessertQuery(dessertId)).execute().single()
        return response.data?.dessert?.toDessertDetail()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    // 1 : Create a coroutine function to send DessertInput data and return an optional Dessert object.
    suspend fun newDessert(dessertInput: DessertInput): Dessert? {
        // 2 : For the response object, we can send a generated mutation called NewDessertMutation, and execute it on the apollo client.
        val response = apolloClient.mutate(NewDessertMutation(dessertInput)).execute().single()
        // 3 : Return the Dessert response object from the GraphQL server, and map it to our client-side models using toDessert().
        return response.data?.createDessert?.toDessert()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun updateDessert(dessertId: String, dessertInput: DessertInput): Dessert? {
        val response = apolloClient.mutate(UpdateDessertMutation(dessertId, dessertInput)).execute().single()
        return response.data?.updateDessert?.toDessert()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun deleteDessert(dessertId: String): Boolean? {
        val response = apolloClient.mutate(DeleteDessertMutation(dessertId)).execute().single()
        return response.data?.deleteDessert
    }

    fun saveFavorite(dessert: Dessert) {
        database.saveDessert(dessert)
    }

    fun removeFavorite(dessertId: String) {
        database.deleteDessert(dessertId)
    }

    fun updateFavorite(dessert: Dessert) {
        database.updateDessert(dessert)
    }

    fun getFavoriteDessert(dessertId: String): Dessert? {
        return database.getDessertById(dessertId)
    }

    fun getFavoriteDesserts(): List<Dessert> {
        return database.getDesserts()
    }
}