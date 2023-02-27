//
//  DessertReviewViewModel.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class DessertReviewViewModel: ViewModel, ObservableObject {
    @Published var review: Review?
    
    let reviewRepository: ReviewRepository
    
    init(reviewRepository: ReviewRepository) {
        self.reviewRepository = reviewRepository
    }
    
    func createReview(dessertId: String, newReview: Review) {
        reviewRepository.doNewReview(dessertId: dessertId, reviewInput: ReviewInput(rating: Int32(newReview.rating), text: newReview.text)) {[weak self] (data, error) in
            guard let self = self,
                  let newReview = data else {return}
            self.review = newReview
        }
    }
    
    func updateReview(review: Review) {
        reviewRepository.updateReview(reviewId: review.id, reviewInput: ReviewInput(rating: Int32(review.rating), text: review.text)) {[weak self] (data, error) in
            guard let self = self,
                  let updateReview = data else {return}
            self.review = updateReview
        }
    }
    
    func deleteReview(reviewId: String) {
        reviewRepository.deleteReview(reviewId: reviewId) { [weak self] (data, error) in
            guard let self = self else {return}
            self.review = nil
        }
    }
}
