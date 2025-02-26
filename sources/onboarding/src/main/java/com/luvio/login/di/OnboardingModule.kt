package com.luvio.login.di

import androidx.lifecycle.ViewModelProvider
import com.luvio.api.LoginMediator
import com.luvio.login.viewmodel.OnboardingViewModelFactory
import dagger.*
import javax.inject.Provider

@Module
interface OnboardingModule {

    @Binds
    fun bindsOnboardingViewModelFactory(onboardingViewModelFactory: OnboardingViewModelFactory): ViewModelProvider.Factory

    companion object {

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): LoginMediator {
            return map[LoginMediator::class.java]!!.get() as LoginMediator
        }
    }
}