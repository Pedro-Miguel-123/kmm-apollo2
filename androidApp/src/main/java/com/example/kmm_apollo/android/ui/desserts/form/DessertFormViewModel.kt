package com.example.kmm_apollo.android.ui.desserts.form

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.cache.DessertDetail
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmm_apollo.type.DessertInput
import com.example.kmmapollo.shared.cache.Dessert
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
class DessertFormViewModel constructor(private val repository: DessertRepository): ViewModel() {

    suspend fun getDessert(dessertId: String): DessertDetail? {
        return repository.getDessert(dessertId)
    }
    suspend fun createDessert(dessert: Dessert): Dessert? {
        return repository.newDessert(DessertInput(name = dessert.name, description = dessert.description, imageUrl = dessert.imageUrl))
    }
    suspend fun updateDessert(dessert: Dessert): Dessert? {
        return repository.updateDessert(dessert.id, DessertInput(name = dessert.name, description = dessert.description, imageUrl = dessert.imageUrl))
    }
    suspend fun deleteDessert(dessertId: String): Boolean? {
        return repository.deleteDessert(dessertId)
    }
}