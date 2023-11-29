package com.luismipalos.delivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.luismipalos.delivery.core.navigation.Navigation
import com.luismipalos.delivery.details.presentation.DetailsScreenViewModel
import com.luismipalos.delivery.homescreen.presentation.HomeScreenViewModel
import com.luismipalos.delivery.signin.presentation.SignInViewModel
import com.luismipalos.delivery.signup.presentation.SignUpViewModel
import com.luismipalos.delivery.ui.theme.DeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val detailsViewModel: DetailsScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryTheme {
                Navigation(
                    signInViewModel,
                    signUpViewModel,
                    homeScreenViewModel,
                    detailsViewModel
                )
            }
        }
    }
}