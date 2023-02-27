//
//  DessertReviewView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 22/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SwiftUI

struct DessertReviewView: View {
    
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: DessertReviewViewModel.self)
    let review: Review?
    
    @Environment(\.presentationMode) var presentationMode
    var body: some View {
        VStack {
            ReviewForm(handler: { review, action in
                switch action {
                case .create:
                    viewModel.createReview(dessertId: review.dessertId, newReview: review)
                case .update:
                    viewModel.updateReview(review: review)
                case .delete_:
                    viewModel.deleteReview(reviewId: review.id)
                default:
                    break
                }
                presentationMode.wrappedValue.dismiss()
            },
           reviewId: review?.id ?? "new", dessertId: review?.dessertId ?? "new", text: review?.text ?? "", rating: review?.rating ?? 0)
        }
        .navigationBarTitle("", displayMode: .inline)
        .onAppear() {
            self.viewModel.review = review
        }
    }
}

