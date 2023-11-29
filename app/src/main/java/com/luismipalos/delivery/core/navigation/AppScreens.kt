package com.luismipalos.delivery.core.navigation

sealed class AppScreens(val route: String) {
    object SigninScreen: AppScreens("signin_screen")
    object SignupScreen: AppScreens("signup_screen")
    object HomeScreen: AppScreens("home_screen")
    object DetailScreen: AppScreens("detail_screen")
}
