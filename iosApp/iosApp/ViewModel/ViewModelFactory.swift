//
//  ViewModelFactory.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
/// Base protocol for all ViewModels
protocol ViewModel {}

/// ViewModel Factory Builder - building the viewModels and injecting their dependecies
class ViewModelFactory
{
    private static var moduleProvider: ModuleProvider {
        return (UIApplication.shared.delegate as! AppDelegate).moduleProvider
    }
    
    static func viewModel<T: ViewModel>(forType type: T.Type) -> T
    {
        switch T.self
        {
        case is DessertListViewModel.Type:
            return self.createDessertListViewModel() as! T
            
        case is DessertDetailViewModel.Type:
            return self.createDessertDetailViewModel() as! T
            
        case is DessertFavoriteViewModel.Type:
            return self.createDessertFavoriteViewModel() as! T
            
        case is LoginViewModel.Type:
            return self.createLoginViewModel() as! T
            
        case is ProfileViewModel.Type:
            return self.createProfileViewModel() as! T
            
        case is CreateDessertViewModel.Type:
            return self.createDessertFormViewModel() as! T
            
        case is DessertReviewViewModel.Type:
            return self.createReviewFormViewModel() as! T
            
        default:
            fatalError("must have a valid view model class")
        }
    }
}

// MARK:- Builder Helpers
private extension ViewModelFactory
{
    static func createDessertListViewModel() -> DessertListViewModel
    {
        return DessertListViewModel(dessertRepository: self.moduleProvider.dessertRepository)
    }
    static func createDessertDetailViewModel() -> DessertDetailViewModel
    {
        return DessertDetailViewModel(dessertRepository: self.moduleProvider.dessertRepository, authRepository: self.moduleProvider.authRepository)
    }
    static func createDessertFavoriteViewModel() -> DessertFavoriteViewModel
    {
        return DessertFavoriteViewModel(dessertRepository: self.moduleProvider.dessertRepository)
    }
    static func createLoginViewModel() -> LoginViewModel
    {
        return LoginViewModel(authRepository: self.moduleProvider.authRepository)
    }
    static func createProfileViewModel() -> ProfileViewModel
    {
        return ProfileViewModel(authRepository: self.moduleProvider.authRepository)
    }
    static func createDessertFormViewModel() -> CreateDessertViewModel
    {
        return CreateDessertViewModel(dessertRepository: self.moduleProvider.dessertRepository)
    }
    static func createReviewFormViewModel() -> DessertReviewViewModel
    {
        return DessertReviewViewModel(reviewRepository: self.moduleProvider.reviewRepository)
    }
}
