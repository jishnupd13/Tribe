package com.app.tribewac.data.models.createanswermodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedAnswerModel(
    @SerialName("question")
    val question: String?,

    @SerialName("user")
    val user: User
)

@Serializable
data class User(
    @SerialName("_id")
    val id: String?
)