package com.luvio.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomNavItem(
    val route: Any,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {

    fun getNavRoute(): String = route.toString()

    data object Home : BottomNavItem(
        WorkspaceScreen.Home,
        com.luvio.ui_atoms.R.drawable.ic_home,
        com.luvio.ui_core.R.string.bottom_home
    )

    data object Map : BottomNavItem(
        WorkspaceScreen.LuvioMap,
        com.luvio.ui_atoms.R.drawable.ic_map_paper,
        com.luvio.ui_core.R.string.bottom_map
    )

    data object Favorites : BottomNavItem(
        WorkspaceScreen.Favorites,
        com.luvio.ui_atoms.R.drawable.ic_favorites,
        com.luvio.ui_core.R.string.bottom_favorites
    )

    data object Profile : BottomNavItem(
        WorkspaceScreen.Profile,
        com.luvio.ui_atoms.R.drawable.ic_profile,
        com.luvio.ui_core.R.string.bottom_profile
    )
}