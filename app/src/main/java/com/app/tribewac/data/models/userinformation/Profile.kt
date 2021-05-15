package com.app.tribewac.data.models.userinformation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("badge")
    val badge: Badge?,
    @SerialName("banner")
    val banner: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("phone")
    val phone: String?,
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