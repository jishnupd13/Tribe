package com.app.tribewac.data.models.specificuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSpecificUserModel(
    @SerialName("followed")
    val followed: Boolean?=false,
    @SerialName("_id")
    val id: String?="",
    @SerialName("id")
    val ids: String?="",
    @SerialName("profile")
    val profile: Profile?
)