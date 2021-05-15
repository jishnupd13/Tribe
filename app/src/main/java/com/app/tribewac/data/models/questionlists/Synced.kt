package com.app.tribewac.data.models.questionlists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Synced(
    @SerialName("search")
    val search: Boolean?,
    @SerialName("searchFailed")
    val searchFailed: Boolean?
)