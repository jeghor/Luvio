package com.luvio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luvio.login.Login
import com.luvio.login.Onboarding
import com.luvio.login.screen.LoginScreen
import com.luvio.login.screen.OnboardingScreen
import com.luvio.ui_core.theme.AppTheme
import com.luvio.ui_core.theme.LuvioTheme

@Composable
fun LuvioApp(
    navHostController: NavHostController
) {
    LuvioTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colors.background
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = Onboarding
                ) {
                    composable<Onboarding> {
                        OnboardingScreen(navHostController)
                    }
                    composable<Login> {
                        LoginScreen(navHostController)
                    }
                }
            }
        }
    }
}