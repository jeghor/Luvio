package com.luvio

import android.app.Application
import com.luvio.api.di.NetworkProvider
import com.luvio.core.api.mediator.AppProvider
import com.luvio.core.api.mediator.ProvidersFacade
import com.luvio.login.LoginExternalModule
import com.luvio.network.models.NetworkProvidersFactory
import dagger.Component

@Component(
    dependencies = [AppProvider::class, NetworkProvider::class],
    modules = [LoginExternalModule::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .networkProvider(
                    NetworkProvidersFactory.createNetworkBuilder(AppComponent.create(application))
                )
                .build()
    }
}