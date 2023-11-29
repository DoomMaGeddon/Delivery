package com.luismipalos.delivery.signup.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.delivery.signup.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _enableSignup = MutableLiveData<Boolean>()
    val enableSignup: LiveData<Boolean> = _enableSignup

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _failedSignUp = MutableLiveData<Boolean>()
    val failedSignUp: LiveData<Boolean> = _failedSignUp

    private val _successfulSignUp = MutableLiveData<Boolean>()
    val successfulSignUp: LiveData<Boolean> = _successfulSignUp

    fun onSignupChanged(name: String, email: String, pass: String) {
        _name.value = name
        _email.value = email
        _pass.value = pass
        _enableSignup.value = isValidName(name) && isValidEmail(email) && isValidPass(pass)
    }

    private fun isValidName(name: String): Boolean =
        "^[A-Z][a-zA-Z]+(?: [A-Z][a-zA-Z]*){0,2}\$".toRegex().containsMatchIn(name)

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPass(pass: String): Boolean = pass.length > 6

    suspend fun onSignupSelected() {
        if (signUpUseCase(_name.value!!, _email.value!!, _pass.value!!)) {
            _successfulSignUp.value = true

        } else
            _failedSignUp.value = true
    }

    suspend fun onSigninSelected(navController: NavController) {
        _isLoading.value = true
        delay(1000)
        navController.popBackStack()
        _isLoading.value = false
    }

    fun dismissErrorDialog() {
        _failedSignUp.value = false
    }

    suspend fun dismissSuccessDialog(navController: NavController) {
        _isLoading.value = true
        delay(1000)
        navController.popBackStack()
        _isLoading.value = false
    }
}