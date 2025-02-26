package com.luvio.login.di

import com.luvio.core.api.mediator.ProvidersFacade
import com.luvio.login.viewmodel.OnboardingViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [OnboardingModule::class],
    dependencies = [ProvidersFacade::class]
)
interface OnboardingComponent {

    fun viewModelFactory(): OnboardingViewModelFactory

    companion object {

        fun create(providersFacade: ProvidersFacade): OnboardingComponent {
            return DaggerOnboardingComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
}