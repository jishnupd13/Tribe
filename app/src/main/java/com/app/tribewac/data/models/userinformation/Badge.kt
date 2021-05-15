package com.app.tribewac.data.models.userinformation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Badge(
    @SerialName("type")
    val type: String?
)