package com.luvio.login

import com.luvio.api.LoginMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface LoginExternalModule {

    @Binds
    @IntoMap
    @ClassKey(LoginMediator::class)
    fun bindMediator(mediator: LoginMediatorImpl): Any
}