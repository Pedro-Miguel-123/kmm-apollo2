package com.example.kmm_apollo.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.platform.setContent
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.navigation.compose.*
import coil.annotation.ExperimentalCoilApi
import com.apollographql.apollo.api.ApolloExperimental
import com.example.kmm_apollo.android.ui.auth.login.LoginView
import com.example.kmm_apollo.android.ui.desserts.detail.DessertDetailView
import com.example.kmm_apollo.android.ui.desserts.favorites.FavoriteListView
import com.example.kmm_apollo.android.ui.desserts.form.DessertFormView
import com.example.kmm_apollo.android.ui.desserts.list.DessertListView
import com.example.kmm_apollo.android.ui.desserts.review.ReviewFormView
import com.example.kmm_apollo.shared.cache.ActionType
import kotlinx.coroutines.ExperimentalCoroutinesApi

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object DessertsScreen : Screens("Desserts", "Desserts", Icons.Default.List)
    object DessertDetailsScreen : Screens("DessertDetails", "DessertDetails")
    object DessertFormScreen : Screens("DessertForm", "DessertForm")
    object ReviewFormScreen : Screens("ReviewForm", "ReviewForm")
    object FavoritesScreen : Screens("Favorites", "Favorites", Icons.Default.Favorite)
    object LoginScreen : Screens("Profile", "Profile", Icons.Default.AccountCircle)
}

@ApolloExperimental
@ExperimentalCoroutinesApi
@ExperimentalCoilApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainLayout()
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ApolloExperimental
@Composable
fun MainLayout() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(Screens.DessertsScreen, Screens.FavoritesScreen, Screens.LoginScreen)
    val bottomBar: @Composable () -> Unit = { BottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.DessertsScreen.route) {
        composable(Screens.DessertsScreen.route) {
            DessertListView(bottomBar, dessertSelected = {
                navController.navigate(Screens.DessertDetailsScreen.route +
                        "/${it.id}")
            })
        }

        composable(Screens.DessertDetailsScreen.route + "/{id}") { backStackEntry ->
            DessertDetailView(backStackEntry.arguments?.get("id") as String,
                editDessertSelected = {
                    navController.navigate(Screens.DessertFormScreen.route +
                            "/${it}")
                }, popBack = { navController.popBackStack() },
                editReviewSelected = {
                    navController.navigate(Screens.ReviewFormScreen.route +
                            "?reviewId=${it}")
                },
                createReviewSelected = {
                    navController.navigate(Screens.ReviewFormScreen.route + "?dessertId=${it}")
                }
            )
        }

        composable(Screens.DessertFormScreen.route + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.get("id") as? String ?: "new"
            val action = if (id != "new") ActionType.UPDATE else ActionType.CREATE
            DessertFormView(id, action,
                popBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.ReviewFormScreen.route + "?reviewId={reviewId}&dessertId={dessertId}") { backStackEntry ->
            val reviewId = backStackEntry.arguments?.get("reviewId") as? String ?: "new"
            val dessertId = backStackEntry.arguments?.get("dessertId") as? String ?: "new"
            val action = if (reviewId != "new") ActionType.UPDATE else ActionType.CREATE
            ReviewFormView(reviewId, dessertId, action,
                popBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.FavoritesScreen.route) {
            FavoriteListView(bottomBar, dessertSelected = {
                navController.navigate(Screens.DessertDetailsScreen.route +
                        "/${it.id}")
            })
        }

        composable(Screens.LoginScreen.route) {
            LoginView(bottomBar, dessertSelected = {
                navController.navigate(Screens.DessertDetailsScreen.route +
                        "/${it.id}")
            }, newDessertSelected = {
                navController.navigate(Screens.DessertFormScreen.route + "/new")
            })
        }
    }
}


@Composable
private fun BottomNavigation(
    navController: NavHostController,
    items: List<Screens>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon) } },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }

}

//@Composable
//private fun currentRoute(navController: NavHostController): String? {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
//}