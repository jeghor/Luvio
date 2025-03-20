package com.luvio.api

object NetworkRoutes {

    object Auth {
        private const val AUTH_PATH = "/auth"

        const val REGISTER = "${BaseUrl.URL + AUTH_PATH}/register"
        const val VERIFY_EMAIL = "${BaseUrl.URL + AUTH_PATH}/auth/verify-email"
        const val LOGIN = "${BaseUrl.URL+ AUTH_PATH}/login"
    }
}