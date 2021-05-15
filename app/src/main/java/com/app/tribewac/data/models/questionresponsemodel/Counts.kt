package com.app.tribewac.data.models.questionresponsemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Counts(
    @SerialName("answers")
    val answers: Int?,
    @SerialName("asks")
    val asks: Int?,
    @SerialName("bookmarks")
    val bookmarks: Int?,
    @SerialName("comments")
    val comments: Int?,
    @SerialName("downvotes")
    val downvotes: Int?,
    @SerialName("edits")
    val edits: Int?,
    @SerialName("followers")
    val followers: Int?,
    @SerialName("hiddenAnswers")
    val hiddenAnswers: Int?,
    @SerialName("pollVotes")
    val pollVotes: Int?,
    @SerialName("upvotes")
    val upvotes: Int?,
    @SerialName("views")
    val views: Int?
)