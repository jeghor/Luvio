package com.luvio.login.di

import com.luvio.core.api.mediator.ProvidersFacade
import com.luvio.login.viewmodel.RegistrationViewModelFactory
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [RegistrationModule::class]
)
interface RegistrationComponent {

    fun viewModelFactory(): RegistrationViewModelFactory

    companion object {

        fun create(providersFacade: ProvidersFacade): RegistrationComponent {
            return DaggerRegistrationComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
}