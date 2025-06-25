package com.luvio.dashboard

import kotlinx.serialization.Serializable

sealed interface WorkspaceScreen {

    @Serializable
    data object Workspace : WorkspaceScreen

    @Serializable
    data object Home : WorkspaceScreen

    @Serializable
    data object LuvioMap : WorkspaceScreen

    @Serializable
    data object Favorites : WorkspaceScreen

    @Serializable
    data object Profile : WorkspaceScreen
}