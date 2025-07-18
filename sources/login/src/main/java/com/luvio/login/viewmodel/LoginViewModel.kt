package com.luvio.login.viewmodel

import androidx.lifecycle.*
import com.luvio.api.NetworkResult
import com.luvio.api.WorkspaceMediator
import com.luvio.api.api.AuthService
import com.luvio.api.model.auth.LoginRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(
    private val authService: AuthService,
    private val workspaceMediator: WorkspaceMediator
) : ViewModel() {

    private val _eventIncorrectData = MutableSharedFlow<Unit>()
    val eventIncorrectData = _eventIncorrectData.asSharedFlow()

    private val _eventSomethingWentWrong = MutableSharedFlow<Unit>()
    val eventSomethingWentWrong = _eventSomethingWentWrong.asSharedFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = authService.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            when (result) {
                is NetworkResult.Success<*> -> workspaceMediator.openWorkspace()

                is NetworkResult.Error -> {
                    if (result.statusCode.value == 404) {
                        _eventIncorrectData.emit(Unit)
                    } else {
                        _eventSomethingWentWrong.emit(Unit)
                    }
                }

                is NetworkResult.Exception -> _eventSomethingWentWrong.emit(Unit)
            }
        }
    }
}

class LoginViewModelFactory @Inject constructor(
    private val authService: AuthService,
    private val workspaceMediator: WorkspaceMediator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(authService, workspaceMediator) as T
    }
}