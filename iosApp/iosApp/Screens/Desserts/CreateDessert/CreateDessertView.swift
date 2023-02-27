//
//  CreateDessertView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 22/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct CreateDessertView : View {
    
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: CreateDessertViewModel.self)
    
    let dessert: Dessert?
    
    @Environment(\.presentationMode) var presentationMode
    
    var body: some View {
        VStack {
            DessertForm(handler: {dessert, action in
                switch action {
                case .create:
                    viewModel.createDessert(newDessert: dessert)
                case .update:
                    viewModel.updateDessert(dessert: dessert)
                case .delete_:
                    viewModel.deleteDessert(dessertId: dessert.id)
                    
                default:
                    break
                }
                presentationMode.wrappedValue.dismiss()
            },
            dessertId: dessert?.id ?? "new", name: dessert?.name ?? "", description: dessert?.description_ ?? "", imageUrl: dessert?.imageUrl ?? "")
        }
        .navigationBarTitle("", displayMode: .inline)
        .onAppear() {
            self.viewModel.dessert = dessert
        }
    }
}
