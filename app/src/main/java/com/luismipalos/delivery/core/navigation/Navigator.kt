package com.luismipalos.delivery.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luismipalos.delivery.details.presentation.DetailsScreen
import com.luismipalos.delivery.details.presentation.DetailsScreenViewModel
import com.luismipalos.delivery.homescreen.presentation.HomeScreen
import com.luismipalos.delivery.homescreen.presentation.HomeScreenViewModel
import com.luismipalos.delivery.signin.presentation.SignInScreen
import com.luismipalos.delivery.signin.presentation.SignInViewModel
import com.luismipalos.delivery.signup.presentation.SignUpScreen
import com.luismipalos.delivery.signup.presentation.SignUpViewModel

@Composable
fun Navigation(
    signInViewModel: SignInViewModel,
    signUpViewModel: SignUpViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    detailsViewModel: DetailsScreenViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SigninScreen.route) {
        composable(route = AppScreens.SigninScreen.route) {
            SignInScreen(signInViewModel, navController)
        }

        composable(route = AppScreens.SignupScreen.route) {
            SignUpScreen(signUpViewModel, navController)
        }

        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(homeScreenViewModel, navController)
        }

        composable(route = AppScreens.DetailScreen.route + "/{dish}",
            arguments = listOf(navArgument(name = "dish") {
                type = NavType.StringType
            })) {
                DetailsScreen(detailsViewModel, navController, it.arguments?.getString("dish"))
        }
    }
}