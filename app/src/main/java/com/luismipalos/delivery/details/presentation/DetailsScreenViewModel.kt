package com.luismipalos.delivery.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.luismipalos.delivery.details.data.network.response.DetailsResponse
import com.luismipalos.delivery.details.domain.DetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val detailsUseCase: DetailsUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dish: MutableStateFlow<DetailsResponse?> = MutableStateFlow(null)
    val dish = _dish

    fun onReturnSelected(navController: NavController) {
        navController.popBackStack()
    }

    fun getDetails(name: String) {
        viewModelScope.launch {_dish.value = detailsUseCase(name)}
    }
}