package com.luismipalos.delivery.homescreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.delivery.core.navigation.AppScreens
import com.luismipalos.delivery.homescreen.data.network.dto.Restaurant
import com.luismipalos.delivery.homescreen.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _restaurants = MutableStateFlow(emptyList<Restaurant>())
    val restaurants = _restaurants.asStateFlow()

    suspend fun onPlateSelected(navController: NavController, dish: String) {
        delay(1000)

        navController.navigate(route = AppScreens.DetailScreen.route + "/" + dish)
    }

    suspend fun getRestaurants() {
        _restaurants.value = homeUseCase()
    }
}