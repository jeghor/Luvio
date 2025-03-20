package com.luvio.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.compose.*
import com.luvio.ui_core.theme.AppTheme

@Composable
fun WorkspaceScreen(
    parentNavController: NavHostController
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Home> {
                TestScreen(stringResource(BottomNavItem.Home.title))
            }
            composable<LuvioMap> {
                TestScreen(stringResource(BottomNavItem.Map.title))
            }
            composable<Favorites> {
                TestScreen(stringResource(BottomNavItem.Favorites.title))
            }
            composable<Profile> {
                TestScreen(stringResource(BottomNavItem.Profile.title))
            }
        }
    }
}

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
        containerColor = AppTheme.colors.background,

        ) {
        items.forEach { item ->
            val title = stringResource(item.title)
            NavigationBarItem(
                selected = currentDestination?.route == item.getNavRoute(),
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