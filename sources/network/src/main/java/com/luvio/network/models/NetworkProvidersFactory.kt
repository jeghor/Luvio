package com.luvio.network.models

import com.luvio.api.di.NetworkProvider
import com.luvio.core.api.mediator.AppProvider
import com.luvio.impl.DaggerNetworkComponent

object NetworkProvidersFactory {

    fun createNetworkBuilder(appProvider: AppProvider): NetworkProvider =
        DaggerNetworkComponent.builder().appProvider(appProvider).build()
}