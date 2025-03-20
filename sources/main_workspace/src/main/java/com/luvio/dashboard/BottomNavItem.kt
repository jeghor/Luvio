package com.luvio.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomNavItem(
    val route: Any,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {

    fun getNavRoute(): String = route.toString().substringBefore("@")

    data object Home : BottomNavItem(
        com.luvio.dashboard.Home,
        com.luvio.ui_atoms.R.drawable.ic_home,
        com.luvio.ui_core.R.string.bottom_home
    )

    data object Map : BottomNavItem(
        LuvioMap,
        com.luvio.ui_atoms.R.drawable.ic_map_paper,
        com.luvio.ui_core.R.string.bottom_map
    )

    data object Favorites : BottomNavItem(
        com.luvio.dashboard.Favorites,
        com.luvio.ui_atoms.R.drawable.ic_favorites,
        com.luvio.ui_core.R.string.bottom_favorites
    )

    data object Profile : BottomNavItem(
        com.luvio.dashboard.Profile,
        com.luvio.ui_atoms.R.drawable.ic_profile,
        com.luvio.ui_core.R.string.bottom_profile
    )
}