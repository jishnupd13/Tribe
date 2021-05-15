package com.app.tribewac.data.models.answersmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("_id")
    val id: String?,
    @SerialName("profile")
    val profile: Profile?
)