package com.luvio.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luvio.api.LoginMediator
import javax.inject.Inject

class OnboardingViewModel(
    private val loginMediator: LoginMediator
) : ViewModel() {

    fun openLoginScreen() {
        loginMediator.openStartScreen()
    }
}

class OnboardingViewModelFactory @Inject constructor(
    private val loginMediator: LoginMediator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingViewModel(loginMediator) as T
    }
}