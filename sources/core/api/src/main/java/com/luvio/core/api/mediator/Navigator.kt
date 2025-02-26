package com.luvio.core.api.mediator

import androidx.navigation.NavHostController

interface Navigator {

    fun getController(): NavHostController?
}