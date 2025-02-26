package com.luvio

import android.app.Application
import com.luvio.core.api.mediator.AppProvider
import com.luvio.core.api.mediator.ProvidersFacade
import com.luvio.login.LoginExternalModule
import dagger.Component

@Component(
    dependencies = [AppProvider::class],
    modules = [LoginExternalModule::class]
)
interface FacadeComponent: ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .build()
    }
}