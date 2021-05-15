package com.app.tribewac.data.models.questionlists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("formatType")
    val formatType: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("src")
    val src: String?
)