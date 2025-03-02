package com.luvio.login.viewmodel

import androidx.lifecycle.*
import com.luvio.api.NetworkResult
import com.luvio.api.api.AuthService
import com.luvio.api.model.auth.RegistrationRequest
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface EmailValidation {

    data object Valid : EmailValidation
    data object Invalid : EmailValidation
}

sealed interface PasswordValidation {

    data object Valid : PasswordValidation
    data object Invalid : PasswordValidation
    data object Mismatch : PasswordValidation
}

const val passwordRegex =
    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}$"
const val emailRegex = "^[A-Za-z\\d._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}$"

class RegistrationViewModel(
    private val authService: AuthService
) : ViewModel() {

    private val _eventError = MutableSharedFlow<String>()
    val eventError = _eventError.asSharedFlow()

    private val _eventSuccessRegistration = MutableSharedFlow<Unit>()
    val eventSuccessRegistration = _eventSuccessRegistration.asSharedFlow()

    private val _stateFirstPassword = MutableStateFlow("")
    private val _stateSecondPassword = MutableStateFlow("")
    private val _stateEmail = MutableStateFlow("")

    val stateEmailValidation: Flow<EmailValidation?> = _stateEmail
        .map {
            if (it.matches(emailRegex.toRegex())) {
                EmailValidation.Valid
            } else {
                EmailValidation.Invalid
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val stateFirstPasswordValidation: Flow<PasswordValidation?> = _stateFirstPassword
        .map {
            if (it.matches(passwordRegex.toRegex())) {
                PasswordValidation.Valid
            } else {
                PasswordValidation.Invalid
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val stateSecondPasswordValidation: Flow<PasswordValidation?> = _stateSecondPassword
        .map {
            if (it.matches(passwordRegex.toRegex())) {
                PasswordValidation.Valid
            } else {
                PasswordValidation.Invalid
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val stateCombinedPasswordValidation: Flow<PasswordValidation> = combine(
        _stateFirstPassword,
        _stateSecondPassword,
        stateFirstPasswordValidation,
        stateSecondPasswordValidation
    ) { first, second, firstValidation, secondValidation ->
        when {
            firstValidation != PasswordValidation.Valid || secondValidation != PasswordValidation.Valid -> PasswordValidation.Invalid
            first != second -> PasswordValidation.Mismatch
            else -> PasswordValidation.Valid
        }
    }

    fun updateEmail(email: String) = _stateEmail.update { email }

    fun updateFirstPassword(password: String) = _stateFirstPassword.update { password }

    fun updateSecondPassword(password: String) = _stateSecondPassword.update { password }

    fun startRegistration(email: String, password: String) {
        viewModelScope.launch {
            val result = authService.register(
                RegistrationRequest(
                    phone = "",
                    email = email,
                    password = password
                )
            )

            when (result) {
                is NetworkResult.Success -> {
                    _eventSuccessRegistration.emit(Unit)
                }
                is NetworkResult.Error -> {
                    val errorText = result.error.message.firstOrNull() ?: ""
                    _eventError.emit(errorText)
                }
                is NetworkResult.Exception -> {
                    _eventError.emit("")
                }
            }
        }
    }
}

class RegistrationViewModelFactory @Inject constructor(
    private val authService: AuthService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(authService) as T
    }
}
