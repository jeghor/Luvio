package com.luvio.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.luvio.dashboard.*
import com.luvio.home.screen.HomeScreen
import com.luvio.login.Login
import com.luvio.login.Onboarding
import com.luvio.login.Registration
import com.luvio.login.screen.LoginScreen
import com.luvio.login.screen.OnboardingScreen
import com.luvio.login.screen.RegistrationScreen
import com.luvio.map.screen.MapScreen
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
                modifier = Modifier.fillMaxSize()
            ) {
                NavHost(
                    navController = navHostController,
                    startDestination = Onboarding
                ) {
                    composable<Onboarding> {
                        OnboardingScreen()
                    }

                    composable<Login> {
                        LoginScreen(navHostController)
                    }

                    composable<Registration>(
                        enterTransition = { customSlideInHorizontally(isPop = false) },
                        exitTransition = { customSlideOutHorizontally(isPop = false) },
                        popEnterTransition = { customSlideInHorizontally(isPop = true) },
                        popExitTransition = { customSlideOutHorizontally(isPop = true) }
                    ) {
                        RegistrationScreen(navHostController)
                    }

                    composable<WorkspaceScreen.Workspace> {
                        WorkspaceScreen(
                            screensMap = mapOf(
                                WorkspaceScreen.Home to {
                                    HomeScreen()
                                },
                                WorkspaceScreen.LuvioMap to {
                                    MapScreen()
                                },
                                WorkspaceScreen.Favorites to {
                                    TestScreen(stringResource(BottomNavItem.Favorites.title))
                                },
                                WorkspaceScreen.Profile to {
                                    TestScreen(stringResource(BottomNavItem.Profile.title))
                                },
                            )
                        )
                    }

                }
            }
        }
    }
}