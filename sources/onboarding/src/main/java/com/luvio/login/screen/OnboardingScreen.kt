package com.luvio.login.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.luvio.ui_core.custom_views.LuvioButton

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    Box(
        Modifier.fillMaxSize()
    ) {
        LuvioButton(
            modifier = Modifier.align(Alignment.Center),
            text = "Start!"
        ) {

        }
    }
}