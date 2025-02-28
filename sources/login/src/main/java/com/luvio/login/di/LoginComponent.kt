package com.luvio.login.di

import com.luvio.core.api.mediator.ProvidersFacade
import com.luvio.login.viewmodel.LoginViewModelFactory
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [LoginModule::class]
)
interface LoginComponent {

    fun viewModelFactory(): LoginViewModelFactory

    companion object {

        fun create(providersFacade: ProvidersFacade): LoginComponent {
            return DaggerLoginComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
}