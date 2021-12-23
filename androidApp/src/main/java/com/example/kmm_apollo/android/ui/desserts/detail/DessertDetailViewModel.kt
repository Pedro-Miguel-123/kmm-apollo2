package com.example.kmm_apollo.android.ui.desserts.detail

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.cache.DessertDetail
import com.example.kmm_apollo.shared.repository.AuthRepository
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmmapollo.shared.cache.Dessert
import com.example.kmmapollo.shared.cache.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DessertDetailViewModel constructor(private val repository: DessertRepository,
                                         private val authRepository: AuthRepository
): ViewModel() {
    @ApolloExperimental
    @ExperimentalCoroutinesApi
    suspend fun getDessert(dessertId: String): DessertDetail? {
        return repository.getDessert(dessertId)
    }

    fun saveFavorite(dessert: Dessert) {
        return repository.saveFavorite(dessert)
    }

    fun updateFavorite(dessert: Dessert) {
        return repository.updateFavorite(dessert)
    }

    fun removeFavorite(dessertId: String) {
        return repository.removeFavorite(dessertId)
    }

    fun getFavorite(dessertId: String): Dessert? {
        return repository.getFavoriteDessert(dessertId)
    }

    fun getUserState(): UserState? {
        return authRepository.getUserState()
    }
}