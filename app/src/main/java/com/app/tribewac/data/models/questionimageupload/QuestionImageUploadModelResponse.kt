package com.app.tribewac.data.models.questionimageupload


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionImageUploadModelResponse(
    @SerialName("uploaded")
    val uploaded: Boolean?,
    @SerialName("url")
    val url: String?
)