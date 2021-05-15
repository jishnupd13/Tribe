package com.app.tribewac.data.models.userinformation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInformationResponseModelItem(
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("email")
    val email: String?="",
    @SerialName("emailStatus")
    val emailStatus: String?="",
    @SerialName("followed")
    val followed: Boolean?,
    @SerialName("lastSeenAt")
    val lastSeenAt: String?="",
    @SerialName("profile")
    val profile: Profile?,
    @SerialName("role")
    val role: String?=null,
    @SerialName("status")
    val status: String?="",
    @SerialName("updatedAt")
    val updatedAt: String?="",
    @SerialName("id")
    val id: String?
)