package com.luvio.login.screen

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.login.di.OnboardingComponent
import com.luvio.login.viewmodel.OnboardingViewModel
import com.luvio.ui_core.custom_views.LuvioButton

@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val component = remember {
        OnboardingComponent.create((application as AppWithFacade).getFacade())
    }

    val viewModel: OnboardingViewModel = viewModel(factory = component.viewModelFactory())

    Box(
        Modifier.fillMaxSize()
    ) {
        LuvioButton(
            modifier = Modifier.align(Alignment.Center),
            text = "Start!"
        ) {
            viewModel.openLoginScreen()
        }
    }
}