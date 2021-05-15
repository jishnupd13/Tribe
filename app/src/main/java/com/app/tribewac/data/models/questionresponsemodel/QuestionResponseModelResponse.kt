package com.app.tribewac.data.models.questionresponsemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponseModelResponse(

    @SerialName("anonymous")
    val anonymous: Boolean?,
    @SerialName("api_url")
    val apiUrl: String?,

    @SerialName("bookmarked")
    val bookmarked: Boolean?,


    @SerialName("counts")
    val counts: Counts?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("description")
    val description: String?,

    @SerialName("followed")
    val followed: Boolean?,

    @SerialName("from")
    val from: String?,
    @SerialName("hasReward")
    val hasReward: Boolean?,

    @SerialName("id")
    val id: String?,
    @SerialName("images")
    val images: List<Image>?,



    @SerialName("lang")
    val lang: String?,
    @SerialName("lastAction")
    val lastAction: LastAction?,
    @SerialName("lastActionAt")
    val lastActionAt: String?,
    @SerialName("lastAskedAt")
    val lastAskedAt: String?,

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
    @SerialName("user")
    val user: UserX?,
    @SerialName("__v")
    val v: Int?,
    @SerialName("verified")
    val verified: Boolean?,

    )