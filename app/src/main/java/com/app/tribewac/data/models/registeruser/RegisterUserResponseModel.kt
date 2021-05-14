package com.app.tribewac.data.models.registeruser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponseModel(
    @SerialName("id")
    val userId: String?
)