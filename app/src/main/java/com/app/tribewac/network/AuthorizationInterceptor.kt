package com.app.tribewac.network

import com.app.tribewac.data.local.PreferencesHandler
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor(private val preferencesHandler: PreferencesHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest() = when (AuthorizationType.fromRequest(this)) {
        AuthorizationType.USER_TOKEN -> {
            newBuilder()
                .header("Authorization", "Bearer ${preferencesHandler.userToken}")
                .build()
        }
        AuthorizationType.NONE -> this
    }

    enum class AuthorizationType {
        USER_TOKEN,
        NONE;

        companion object {
            fun fromRequest(request: Request): AuthorizationType =
                request.tag(AuthorizationType::class.java) ?: NONE
        }
    }

}