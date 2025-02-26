package com.luvio

import android.app.Application
import com.luvio.core.api.mediator.ActivityProvider
import com.luvio.core.impl.mediator.ActivityProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideActivityProvider(app: Application): ActivityProvider {
        return ActivityProviderImpl(app)
    }
}