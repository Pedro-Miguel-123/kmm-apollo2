package com.example.kmm_apollo.shared.cache

import com.example.kmm_apollo.GetDessertQuery
import com.example.kmm_apollo.GetReviewQuery
import com.example.kmm_apollo.NewReviewMutation
import com.example.kmm_apollo.UpdateReviewMutation
import com.example.kmmapollo.shared.cache.Review

fun GetDessertQuery.Review.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)

fun GetReviewQuery.GetReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)

fun NewReviewMutation.CreateReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)


fun UpdateReviewMutation.UpdateReview.toReview() = Review(
    id = id,
    userId = userId,
    dessertId = dessertId,
    text = text,
    rating = rating.toLong()
)