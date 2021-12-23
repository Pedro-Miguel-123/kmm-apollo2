package com.example.kmm_apollo.android.ui.auth.login

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.AuthRepository
import com.example.kmm_apollo.type.UserInput
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ApolloExperimental
class LoginViewModel constructor(private val repository: AuthRepository): ViewModel() {

    suspend fun signIn(email: String, password: String): String {
        return repository.signIn(UserInput(email, password))
    }

    suspend fun signUp(email: String, password: String): String {
        return repository.signUp(UserInput(email = email, password = password))
    }

    fun getAuthToken(): String {
        return repository.getUserState()?.token ?: ""
    }

    fun deleteAuthToken() {
        repository.deleteUserState()
    }
}