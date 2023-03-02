//
//  ProfileView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 27/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import Foundation
import SwiftUI
@available(iOS 14.0, *)
struct ProfileView: View {
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: ProfileViewModel.self)
    
    var logoutHandler: () -> Void
    
    
    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.desserts, id: \.id) { dessert in
                    NavigationLink(destination: DessertDetailView(dessertId: dessert.id)) {
                        DessertListRowView(dessert: dessert)
                    }
                }
            }
            .navigationTitle("Profile")
            .navigationBarItems(trailing:
                HStack {
                    Button( action: {
                        logoutHandler()
                    }, label: {
                        Image(systemName: "arrow.left.circle.fill")
                    })
                    NavigationLink(destination: CreateDessertView(dessert: nil)) {
                        Image(systemName: "plus")
                    }
                }
            )
            .onAppear() {
                viewModel.fetchDesserts()
            }
        }
    }
}
