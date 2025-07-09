package com.luvio.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.luvio.ui_core.theme.AppTheme

@Composable
fun WorkspaceScreen(
    screensMap: Map<WorkspaceScreen, @Composable () -> Unit>
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        },
        containerColor = AppTheme.colors.background
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = WorkspaceScreen.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            screensMap.forEach { (screen, content) ->
                when (screen) {
                    WorkspaceScreen.Home -> {
                        composable<WorkspaceScreen.Home>(
                            enterTransition = { subtleHorizontalSlideIn(isPop = false) },
                            exitTransition = { subtleHorizontalSlideOut(isPop = false) },
                            popEnterTransition = { subtleHorizontalSlideIn(isPop = true) },
                            popExitTransition = { subtleHorizontalSlideOut(isPop = true) }
                        ) {
                            content()
                        }
                    }

                    WorkspaceScreen.LuvioMap -> {
                        composable<WorkspaceScreen.LuvioMap>(
                            enterTransition = { subtleHorizontalSlideIn(isPop = false) },
                            exitTransition = { subtleHorizontalSlideOut(isPop = false) },
                            popEnterTransition = { subtleHorizontalSlideIn(isPop = true) },
                            popExitTransition = { subtleHorizontalSlideOut(isPop = true) }
                        ) {
                            content()
                        }
                    }

                    WorkspaceScreen.Favorites -> {
                        composable<WorkspaceScreen.Favorites>(
                            enterTransition = { subtleHorizontalSlideIn(isPop = false) },
                            exitTransition = { subtleHorizontalSlideOut(isPop = false) },
                            popEnterTransition = { subtleHorizontalSlideIn(isPop = true) },
                            popExitTransition = { subtleHorizontalSlideOut(isPop = true) }
                        ) {
                            content()
                        }
                    }

                    WorkspaceScreen.Profile -> {
                        composable<WorkspaceScreen.Profile>(
                            enterTransition = { subtleHorizontalSlideIn(isPop = false) },
                            exitTransition = { subtleHorizontalSlideOut(isPop = false) },
                            popEnterTransition = { subtleHorizontalSlideIn(isPop = true) },
                            popExitTransition = { subtleHorizontalSlideOut(isPop = true) }
                        ) {
                            content()
                        }
                    }

                    else -> {
                        /*no-op*/
                    }
                }
            }
        }
    }
}

private const val ROUT_DELIMITER = "."

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Map,
        BottomNavItem.Favorites,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = AppTheme.colors.background
    ) {
        items.forEach { item ->
            val title = stringResource(item.title)
            val currentRoute = currentDestination?.route?.substringAfterLast(ROUT_DELIMITER)
            NavigationBarItem(
                selected = currentRoute == item.getNavRoute(),
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = title
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = AppTheme.colors.background,
                    selectedTextColor = AppTheme.colors.background,
                    selectedIndicatorColor = AppTheme.colors.primary,
                    unselectedIconColor = AppTheme.colors.textPrimary,
                    unselectedTextColor = AppTheme.colors.textPrimary,
                    disabledIconColor = AppTheme.colors.background,
                    disabledTextColor = AppTheme.colors.background
                )
            )
        }
    }
}

@Composable
fun TestScreen(title: String) {
    Box { Text(title) }
}