package com.luvio

import com.luvio.core.api.mediator.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent =
            DaggerMainComponent.builder().providersFacade(providersFacade).build()
    }

    fun inject(mainActivity: MainActivity)
}