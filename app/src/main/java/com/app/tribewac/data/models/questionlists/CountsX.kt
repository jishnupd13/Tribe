package com.app.tribewac.data.models.questionlists


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountsX(
    @SerialName("answers")
    val answers: Int?,
    @SerialName("answersVotes")
    val answersVotes: Int?,
    @SerialName("answersWords")
    val answersWords: Int?,
    @SerialName("comments")
    val comments: Int?,
    @SerialName("downloads")
    val downloads: Int?,
    @SerialName("edits")
    val edits: Int?,
    @SerialName("fileUploads")
    val fileUploads: Int?,
    @SerialName("followers")
    val followers: Int?,
    @SerialName("followings")
    val followings: Int?,
    @SerialName("groups")
    val groups: Int?,
    @SerialName("pollVotes")
    val pollVotes: Int?,
    @SerialName("posts")
    val posts: Int?,
    @SerialName("questions")
    val questions: Int?,
    @SerialName("questionsFollowers")
    val questionsFollowers: Int?,
    @SerialName("receivedDownloads")
    val receivedDownloads: Int?,
    @SerialName("receivedLikes")
    val receivedLikes: Int?,
    @SerialName("replies")
    val replies: Int?,
    @SerialName("requests")
    val requests: Int?,
    @SerialName("responses")
    val responses: Int?,
    @SerialName("views")
    val views: Int?
)