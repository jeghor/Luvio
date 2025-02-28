package com.luvio.login.di

import androidx.lifecycle.ViewModelProvider
import com.luvio.login.viewmodel.LoginViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface LoginModule {

    @Binds
    fun bindsLoginViewModelFactory(viewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory
}