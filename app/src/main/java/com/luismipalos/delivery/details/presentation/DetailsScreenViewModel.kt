package com.luismipalos.delivery.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.delivery.details.domain.DetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val detailsUseCase: DetailsUseCase) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _details = MutableStateFlow(emptyList<String>())
    val details = _details.asStateFlow()

    suspend fun onReturnSelected(navController: NavController) {
        delay(1000)
        navController.popBackStack()
    }

    suspend fun getDetails() {
        _details.value = detailsUseCase()
    }
}