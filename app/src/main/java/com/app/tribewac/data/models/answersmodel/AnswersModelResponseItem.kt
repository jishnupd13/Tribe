package com.app.tribewac.data.models.answersmodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnswersModelResponseItem(
    @SerialName("_id")
    val id: String?,
    @SerialName("user")
    val user: User?,
    @SerialName("content")
    val content: String?
)