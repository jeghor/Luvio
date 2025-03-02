package com.luvio.login.di

import androidx.lifecycle.ViewModelProvider
import com.luvio.login.viewmodel.RegistrationViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface RegistrationModule {

    @Binds
    fun bindsRegistrationViewModelFactory(viewModel: RegistrationViewModelFactory): ViewModelProvider.Factory
}