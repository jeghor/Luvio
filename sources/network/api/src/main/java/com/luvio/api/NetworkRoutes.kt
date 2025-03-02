package com.luvio.api

object NetworkRoutes {

    private const val BASE_URL = "https://routeapp.catscode.ru"

    object Auth {
        private const val AUTH_PATH = "/auth"

        const val REGISTER = "${BASE_URL+ AUTH_PATH}/register"
        const val VERIFY_EMAIL = "${BASE_URL+ AUTH_PATH}/auth/verify-email"
        const val LOGIN = "${BASE_URL+ AUTH_PATH}/login"
    }
}