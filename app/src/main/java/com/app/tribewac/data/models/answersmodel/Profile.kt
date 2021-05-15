package com.app.tribewac.data.models.answersmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Profile(
    @SerialName("bio")
    val bio: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("username")
    val username: String?,
    @SerialName("website")
    val website: String?
)