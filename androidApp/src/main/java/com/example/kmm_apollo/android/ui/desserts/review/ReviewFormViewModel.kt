package com.example.kmm_apollo.android.ui.desserts.review

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.shared.repository.ReviewRepository
import com.example.kmm_apollo.type.ReviewInput
import com.example.kmmapollo.shared.cache.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ApolloExperimental
@ExperimentalCoroutinesApi
class ReviewFormViewModel constructor(private val repository: ReviewRepository): ViewModel() {

    suspend fun getReview(reviewId: String): Review? {
        return repository.getReview(reviewId)
    }
    suspend fun createReview(dessertId: String, review: Review): Review? {
        return repository.newReview(dessertId, ReviewInput(text = review.text, rating = review.rating.toInt()))
    }
    suspend fun updateReview(review: Review): Review? {
        return repository.updateReview(review.id, ReviewInput(text = review.text, rating = review.rating.toInt()))
    }
    suspend fun deleteReview(reviewId: String): Boolean? {
        return repository.deleteReview(reviewId)
    }
}