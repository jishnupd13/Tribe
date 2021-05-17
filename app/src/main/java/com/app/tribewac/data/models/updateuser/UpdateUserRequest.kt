package com.app.tribewac.data.models.updateuser

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UpdateUserRequest(
    @SerialName("username")
    val username: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("password")
    val password: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("gender")
    val gender: String?
)