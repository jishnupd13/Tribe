package com.app.tribewac.data.models.getmaintoken


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAppMainTokenModel(
    @SerialName("access_token")
    val accessToken: String?,
    @SerialName("expires_in")
    val expiresIn: Long?,
    @SerialName("refresh_token")
    val refreshToken: String?,
    @SerialName("token_type")
    val tokenType: String?
)