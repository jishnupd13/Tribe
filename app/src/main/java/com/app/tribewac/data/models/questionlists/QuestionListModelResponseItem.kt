package com.app.tribewac.data.models.questionlists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionListModelResponseItem(
    @SerialName("anonymous")
    val anonymous: Boolean?,
    @SerialName("api_url")
    val apiUrl: String?,
    @SerialName("counts")
    val counts: Counts?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("description")
    val description: String?="",
    @SerialName("hasReward")
    val hasReward: Boolean?,
    @SerialName("_id")
    val id: String?,
    @SerialName("images")
    val images: List<Image>?,

    @SerialName("followers")
    val followers: List<String>?,

    @SerialName("lang")
    val lang: String?,
    @SerialName("lastAction")
    val lastAction: LastAction?=null,
    @SerialName("lastActionAt")
    val lastActionAt: String?="",
    @SerialName("lastAskedAt")
    val lastAskedAt: String?="",
    @SerialName("locked")
    val locked: Boolean?,
    @SerialName("portal")
    val portal: String?,
    @SerialName("privacy")
    val privacy: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("score")
    val score: Int?,
    @SerialName("shortId")
    val shortId: String?,
    @SerialName("slug")
    val slug: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("synced")
    val synced: Synced?,
    @SerialName("title")
    val title: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("__v")
    val v: Int?,
    @SerialName("verified")
    val verified: Boolean?,
    @SerialName("followed")
    val followed: Boolean?=false
)