//
//  DessertFavoriteViewModel.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class DessertFavoriteViewModel: ViewModel, ObservableObject {
    
    @Published public var favorites: [Dessert] = []
    
    let dessertRepository: DessertRepository
    
    init(dessertRepository: DessertRepository) {
        self.dessertRepository = dessertRepository
    }
    
    func fetchFavorites() {
        let favorites = self.dessertRepository.getFavoriteDesserts()
        self.favorites = favorites
    }
    
    func onUpdateDessert(updateDessert: Dessert) {
        self.dessertRepository.removeFavorite(dessertId: updateDessert.id)
        self.dessertRepository.saveFavorite(dessert: updateDessert)
        
        let insertIndex = self.favorites.firstIndex { dessert -> Bool in
            return dessert.id == updateDessert.id
        }
        
        if let index = insertIndex {
            self.favorites[index] = updateDessert
        }
    }
    
    func onDeleteDessert(dessertId: String) {
        self.dessertRepository.removeFavorite(dessertId: dessertId)
    }
    
}
