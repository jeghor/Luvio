package com.luvio.dashboard.di

import com.luvio.api.WorkspaceMediator
import com.luvio.dashboard.WorkspaceMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface WorkspaceExternalModule {

    @Binds
    @IntoMap
    @ClassKey(WorkspaceMediator::class)
    fun bindsMediator(mediator: WorkspaceMediatorImpl): Any
}