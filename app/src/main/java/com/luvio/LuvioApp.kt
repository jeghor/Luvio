package com.luvio

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luvio.login.Login
import com.luvio.login.screens.LoginScreen
import com.luvio.ui_core.theme.AppTheme
import com.luvio.ui_core.theme.LuvioTheme

@Composable
fun LuvioApp() {
    LuvioTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colors.background
        ) {
            val navController = rememberNavController()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Login
                ) {
                    composable<Login> {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }
}