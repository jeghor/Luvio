package com.luvio.login

import androidx.navigation.NavController
import com.luvio.api.LoginMediator

class LoginMediatorImpl(private val navController: NavController): LoginMediator {

    override fun openStartScreen() {
        navController.navigate(Login)
    }
}