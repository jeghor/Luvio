package com.luvio.api.di

import com.luvio.api.api.AuthService
import io.ktor.client.HttpClient

interface NetworkProvider {

    fun provideKtorClient(): HttpClient

    fun provideAuthService(): AuthService
}