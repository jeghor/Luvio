package com.luvio.login.di

import androidx.lifecycle.ViewModelProvider
import com.luvio.api.WorkspaceMediator
import com.luvio.login.viewmodel.LoginViewModelFactory
import dagger.*
import javax.inject.Provider

@Module
interface LoginModule {

    @Binds
    fun bindsLoginViewModelFactory(viewModelFactory: LoginViewModelFactory): ViewModelProvider.Factory

    companion object {

        @Provides
        fun providesWorkspaceMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): WorkspaceMediator {
            return map[WorkspaceMediator::class.java]!!.get() as WorkspaceMediator
        }
    }
}