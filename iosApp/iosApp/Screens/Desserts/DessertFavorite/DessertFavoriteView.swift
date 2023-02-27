//
//  DessertFavoriteView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DessertFavoriteView: View {
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: DessertFavoriteViewModel.self)
    
    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.favorites, id: \.id) { dessert in
                    NavigationLink(destination: DessertDetailView(dessertId: dessert.id)) {
                        DessertListRowView(dessert: dessert)
                    }
                }
            }
            .navigationTitle("Favorites")
            .onAppear() {
                viewModel.fetchFavorites()
            }
        }
    }
}

