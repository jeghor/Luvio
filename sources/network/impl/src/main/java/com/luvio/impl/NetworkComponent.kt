package com.luvio.impl

import com.luvio.api.di.NetworkProvider
import com.luvio.core.api.mediator.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent : NetworkProvider