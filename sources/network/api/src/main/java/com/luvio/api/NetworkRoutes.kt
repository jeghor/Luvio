package com.luvio.api

object NetworkRoutes {

    private const val BASE_URL = "https://routeapp.catscode.ru"

    object Auth {
        const val REGISTER = "$BASE_URL/register"
        const val VERIFY_EMAIL = "$BASE_URL/verify-email"
        const val LOGIN = "$BASE_URL/login"
    }
}