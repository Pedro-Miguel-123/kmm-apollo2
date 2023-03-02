//
//  DessertListRowView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 22/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import URLImage

@available(iOS 14.0, *)
struct DessertListRowView: View {
    let dessert: Dessert
    var body: some View {
        HStack {
            if let image = dessert.imageUrl,
               let url = URL(string: image) {
                URLImage(url) { image in
                    image
                        .resizable()
                        .frame(width: 50, height: 50)
                        .cornerRadius(25)
                    
                }
            } else {
                RoundedRectangle(cornerRadius: 25)
                    .frame(width: 50, height: 50)
                    .foregroundColor(.gray)
            }
            VStack(alignment: .leading) {
                Text(dessert.name)
                    .font(.title3)
                    .foregroundColor(.accentColor)
                Text(dessert.description_)
                    .font(.footnote)
                    .foregroundColor(.gray)
            }
        }
    }
}

