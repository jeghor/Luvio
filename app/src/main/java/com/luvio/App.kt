package com.luvio

import android.app.Application
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.core.api.mediator.ProvidersFacade

class App : Application(), AppWithFacade {

    companion object {

        private var facadeComponent: FacadeComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        getFacade()
    }

    override fun getFacade(): ProvidersFacade =
        facadeComponent ?: FacadeComponent.init(this).also { facadeComponent = it }
}