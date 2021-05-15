package com.app.tribewac.data.models.questionlists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class From(
    @SerialName("externalId")
    val externalId: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("id")
    val ids: String?,
    @SerialName("profile")
    val profile: Profile?
)