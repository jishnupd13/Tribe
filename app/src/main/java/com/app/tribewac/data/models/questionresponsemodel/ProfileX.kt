package com.app.tribewac.data.models.questionresponsemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileX(
    @SerialName("badge")
    val badge: BadgeX?,
    @SerialName("banner")
    val banner: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("counts")
    val counts: CountsXX?,
    @SerialName("description")
    val description: String?,
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