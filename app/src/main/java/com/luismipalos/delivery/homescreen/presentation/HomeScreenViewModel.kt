package com.luismipalos.delivery.homescreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.luismipalos.delivery.core.navigation.AppScreens
import com.luismipalos.delivery.homescreen.data.network.response.RestaurantResponse
import com.luismipalos.delivery.homescreen.domain.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _restaurants: MutableStateFlow<List<RestaurantResponse>?>
        = MutableStateFlow(null)
    val restaurants = _restaurants

    fun onPlateSelected(navController: NavController, dish: String) {
        navController.navigate(route = AppScreens.DetailScreen.route + "/" + dish)
    }

    fun getRestaurants() {
        viewModelScope.launch {_restaurants.value = homeUseCase()}
    }
}