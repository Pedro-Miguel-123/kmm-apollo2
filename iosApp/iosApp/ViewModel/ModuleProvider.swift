//
//  ModuleProvider.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

final class ModuleProvider
{
    let dessertRepository: DessertRepository
    let authRepository: AuthRepository
    let reviewRepository: ReviewRepository
    
    private let apolloProvider = ApolloProvider(databaseDriverFactory: DatabaseDriverFactory(), myLogger: MyLogger())
    
    init()
    {
        self.dessertRepository = DessertRepository(apolloProvider: apolloProvider)
        self.authRepository = AuthRepository(apolloProvider: apolloProvider)
        self.reviewRepository = ReviewRepository(apolloProvider: apolloProvider)
        
        self.commonInit()
    }
    
    func commonInit()
    {
      // add any configuration logic here
    }
    
    func start()
    {
        
    }
}
