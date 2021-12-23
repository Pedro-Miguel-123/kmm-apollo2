package com.example.kmm_apollo.android.di

import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.android.ui.auth.login.LoginViewModel
import com.example.kmm_apollo.android.ui.auth.profile.ProfileViewModel
import com.example.kmm_apollo.android.ui.desserts.detail.DessertDetailViewModel
import com.example.kmm_apollo.android.ui.desserts.favorites.FavoriteListViewModel
import com.example.kmm_apollo.android.ui.desserts.form.DessertFormViewModel
import com.example.kmm_apollo.android.ui.desserts.list.DessertListViewModel
import com.example.kmm_apollo.android.ui.desserts.review.ReviewFormViewModel
import com.example.kmm_apollo.shared.ApolloProvider
import com.example.kmm_apollo.shared.cache.DatabaseDriverFactory
import com.example.kmm_apollo.shared.logger.MyLogger
import com.example.kmm_apollo.shared.repository.AuthRepository
import com.example.kmm_apollo.shared.repository.DessertRepository
import com.example.kmm_apollo.shared.repository.ReviewRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@ApolloExperimental
val mainAppModule = module {

    viewModel { DessertListViewModel(get()) }
    viewModel { DessertDetailViewModel(get(), get()) }
    viewModel { DessertFormViewModel(get()) }
    viewModel { ReviewFormViewModel(get()) }
    viewModel { FavoriteListViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }


    single { DessertRepository(get()) }
    single { AuthRepository(get()) }
    single { ReviewRepository(get()) }
    single { ApolloProvider(DatabaseDriverFactory(get()), myLogger = MyLogger()) }
    single { DatabaseDriverFactory(this.androidApplication().applicationContext) }
}

@ApolloExperimental
@ExperimentalCoroutinesApi
val appModule = listOf(mainAppModule)