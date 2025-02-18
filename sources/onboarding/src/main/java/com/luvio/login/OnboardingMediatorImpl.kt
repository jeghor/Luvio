package com.luvio.login

import androidx.navigation.NavController
import com.luvio.api.OnboardingMediator

class OnboardingMediatorImpl(private val navController: NavController) : OnboardingMediator {

    override fun openStartScreen() {
        navController.navigate(Onboarding)
    }
}