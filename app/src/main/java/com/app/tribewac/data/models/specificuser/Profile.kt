package com.app.tribewac.data.models.specificuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("counts")
    val counts: Counts?,
    @SerialName("description")
    val description: String?,
    @SerialName("externalId")
    val externalId: String?="",
    @SerialName("gender")
    val gender: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("picture")
    val picture: String?,
    @SerialName("score")
    val score: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("username")
    val username: String?,
    @SerialName("verified")
    val verified: Boolean?,
    @SerialName("website")
    val website: String?
)