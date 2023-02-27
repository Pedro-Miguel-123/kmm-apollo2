//
//  CreateDessertViewModel.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

class CreateDessertViewModel: ViewModel, ObservableObject {
    @Published var dessert: Dessert?
    
    let dessertRepository: DessertRepository
    
    init(dessertRepository: DessertRepository) {
        self.dessertRepository = dessertRepository
    }
    
    func createDessert(newDessert: Dessert) {
        dessertRepository.doNewDessert(dessertInput: DessertInput(description: newDessert.description_, imageUrl: newDessert.imageUrl, name: newDessert.name)) { [weak self] (data, error) in
            guard let self = self,
                  let newDessert = data else { return }
            self.dessert = newDessert
        }
    }
        
    func updateDessert(dessert: Dessert) {
        dessertRepository.updateDessert(dessertId: dessert.id, dessertInput: DessertInput(description: dessert.description_, imageUrl: dessert.imageUrl, name: dessert.name)) { [weak self] (data, error) in
            guard let self = self,
                  let updatedDessert = data else { return }
            self.dessert = updatedDessert
        }
    }
        
    func deleteDessert(dessertId: String) {
        dessertRepository.deleteDessert(dessertId: dessertId) { [weak self] (deleted, error) in
            guard let self = self else { return }
            self.dessert = nil
        }
    }
}
