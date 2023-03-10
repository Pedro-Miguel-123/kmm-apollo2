//
//  ReviewForm.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 27/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import Foundation
import shared
import SwiftUI

struct ReviewForm: View {
    
    var handler: (Review, ActionType) -> Void
    let reviewId: String
    let dessertId: String
    @State var text: String
    @State var rating: Int64
    
    private var isEditing: Bool {
        return reviewId != "new"
    }
    
    private var label: String {
        return isEditing ? "Edit" : "Create"
    }
    
    var body: some View {
        Form {
            Section(header: Text("\(label) Review")) {
                TextField("Description", text: $text)
                
                VStack {
                    HStack {
                        ForEach(0..<5) { index in
                            if (index <= rating) {
                                Image(systemName: "star.fill").onTapGesture {
                                    self.rating = Int64(index)
                                }
                            } else {
                                Image(systemName: "star").onTapGesture {
                                    self.rating = Int64(index)
                                }
                            }
                        }
                    }
                }
            }
            Section {
                Button(
                    action: {
                        let action: ActionType = isEditing ? .update : .create
                        self.handler(Review(id: reviewId, dessertId: dessertId, userId: "", text: text, rating: rating), action)
                    },
                    label: { Text(label) }
                )
                if isEditing {
                    Button(
                        action: {
                            self.handler(Review(id: reviewId, dessertId: dessertId, userId: "", text: text, rating: rating), ActionType.delete_)
                        },
                        label: { Text("Delete") }
                    )
                }
            }
        }
    }
}
