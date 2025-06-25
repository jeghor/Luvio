package com.luvio

import android.app.Application
import com.luvio.api.di.NetworkProvider
import com.luvio.core.api.mediator.*
import com.luvio.dashboard.di.WorkspaceExternalModule
import com.luvio.login.LoginExternalModule
import com.luvio.network.models.NetworkProvidersFactory
import dagger.Component

@Component(
    dependencies = [AppProvider::class, NetworkProvider::class],
    modules = [LoginExternalModule::class, WorkspaceExternalModule::class]
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