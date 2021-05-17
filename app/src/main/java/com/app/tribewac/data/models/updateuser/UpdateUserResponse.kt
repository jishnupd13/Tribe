package com.app.tribewac.data.models.updateuser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponse(
    @SerialName("username")
    val username: String?=""
)