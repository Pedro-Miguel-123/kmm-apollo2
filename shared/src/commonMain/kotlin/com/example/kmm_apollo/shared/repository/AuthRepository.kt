package com.example.kmm_apollo.shared.repository

import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.GetProfileQuery
import com.example.kmm_apollo.SignInMutation
import com.example.kmm_apollo.SignUpMutation
import com.example.kmm_apollo.shared.ApolloProvider
import com.example.kmm_apollo.shared.cache.toDessert
import com.example.kmm_apollo.type.UserInput
import com.example.kmmapollo.shared.cache.Dessert
import com.example.kmmapollo.shared.cache.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

class AuthRepository(apolloProvider: ApolloProvider): BaseRepository(apolloProvider) {

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun signIn(userInput: UserInput): String {
        val response = apolloClient.mutate(SignInMutation(userInput)).execute().single()
        response.data?.signIn?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            return data.token
        }
        throw Exception("Could not sign in")
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun signUp(userInput: UserInput): String {
        val response = apolloClient.mutate(SignUpMutation(userInput)).execute().single()
        response.data?.signUp?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            return data.token
        }
        throw Exception("Could not sign up")
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getProfileDesserts(): List<Dessert> {
        val response = apolloClient.query(GetProfileQuery()).execute().single()
        return response.data?.getProfile?.desserts?.map { it.toDessert() } ?: emptyList()
    }

    fun getUserState(): UserState? {
        return database.getUserState()
    }

    fun deleteUserState() {
        return database.deleteUserState()
    }
}