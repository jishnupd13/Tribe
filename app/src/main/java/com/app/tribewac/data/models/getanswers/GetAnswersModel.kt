package com.app.tribewac.data.models.getanswers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAnswersModel(
    @SerialName("id")
    val id: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("user")
    val user: User?
)

@Serializable
data class User(
    @SerialName("profile")
    val profile: Profile?
)

@Serializable
data class Profile(
    @SerialName("name")
    val name: Profile?
)
