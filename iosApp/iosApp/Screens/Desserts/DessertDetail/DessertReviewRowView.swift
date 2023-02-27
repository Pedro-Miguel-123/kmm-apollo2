//
//  DessertReviewRowView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 22/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import SwiftUI
import shared
import URLImage

struct DessertReviewRowView: View {
    let review: Review
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(review.text)
                    .font(.title3)
                    .foregroundColor(.accentColor)
                VStack {
                    HStack {
                        ForEach(0..<5) { index in
                            if (index <= review.rating) {
                                Image(systemName: "star.fill")
                            } else {
                                Image(systemName: "star")
                            }
                        }
                    }
                }
            }
        }
    }
}
