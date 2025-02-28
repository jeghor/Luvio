package com.luvio.login.viewmodel

import androidx.lifecycle.*
import com.luvio.api.NetworkResult
import com.luvio.api.api.AuthService
import com.luvio.api.auth.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _stateLogin = MutableStateFlow<String>("")
    val stateLogin = _stateLogin.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authService.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            when (result) {
                is NetworkResult.Success<*> -> {
                    _stateLogin.emit("Success")
                }

                is NetworkResult.Error<*> -> {
                    _stateLogin.emit("Error")
                }

                is NetworkResult.Exception<*> -> {
                    _stateLogin.emit("Exception")
                }
            }
        }
    }
}

class LoginViewModelFactory @Inject constructor(
    private val authService: AuthService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authService) as T
    }
}