package com.example.kmm_apollo.shared.repository

import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.DeleteReviewMutation
import com.example.kmm_apollo.GetReviewQuery
import com.example.kmm_apollo.NewReviewMutation
import com.example.kmm_apollo.UpdateReviewMutation
import com.example.kmm_apollo.shared.ApolloProvider
import com.example.kmm_apollo.shared.cache.toReview
import com.example.kmm_apollo.type.ReviewInput
import com.example.kmmapollo.shared.cache.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

class ReviewRepository(apolloProvider: ApolloProvider): BaseRepository(apolloProvider) {

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun getReview(reviewId: String): Review? {
        val response = apolloClient.query(GetReviewQuery(reviewId)).execute().single()
        return response.data?.getReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun newReview(dessertId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutate(NewReviewMutation(dessertId, reviewInput)).execute().single()
        return response.data?.createReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun updateReview(reviewId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutate(UpdateReviewMutation(reviewId, reviewInput)).execute().single()
        return response.data?.updateReview?.toReview()
    }

    @ExperimentalCoroutinesApi
    @ApolloExperimental
    suspend fun deleteReview(reviewId: String): Boolean? {
        val response = apolloClient.mutate(DeleteReviewMutation(reviewId)).execute().single()
        return response.data?.deleteReview
    }
}