//
//  DessertListView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import Foundation
import SwiftUI
@available(iOS 14.0, *)
struct DessertListView: View {
    
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: DessertListViewModel.self)
    
    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.desserts, id: \.id) { dessert in
                    NavigationLink(destination: DessertDetailView(dessertId: dessert.id)) {
                        DessertListRowView(dessert: dessert)
                    }
                }
                
                if viewModel.shouldDisplayNextPage {
                    nextPageView
                }
            }
            .navigationTitle("Desserts")
            .onAppear() {
                viewModel.currentPage = 0
                viewModel.fetchDesserts()
            }
        }
    }
    
    private var nextPageView: some View {
        HStack {
            Spacer()
            VStack {
                ProgressView()
                Text("Loading")
            }
            Spacer()
        }
        .onAppear(perform: {
            viewModel.currentPage += 1
        })
    }
    
    struct DessertListView_Previews: PreviewProvider {
        static var previews: some View {
            DessertListView()
        }
    }
}
