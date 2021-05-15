package com.app.tribewac.data.models.questionresponsemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LastAction(
    @SerialName("user")
    val user: User?,
    @SerialName("verb")
    val verb: String?
)