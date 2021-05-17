package com.app.tribewac.data.models.specificuser


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Counts(
    @SerialName("answers")
    val answers: Int?=0,
    @SerialName("answersViews")
    val answersViews: Int?=0,
    @SerialName("answersVotes")
    val answersVotes: Int?=0,
    @SerialName("answersWords")
    val answersWords: Int?=0,
    @SerialName("comments")
    val comments: Int?=0,
    @SerialName("edits")
    val edits: Int?=0,
    @SerialName("followers")
    val followers: Int?=0,
    @SerialName("followings")
    val followings: Int?=0,
    @SerialName("groups")
    val groups: Int?=0,
    @SerialName("posts")
    val posts: Int?=0,
    @SerialName("questions")
    val questions: Int?=0,
    @SerialName("questionsFollowers")
    val questionsFollowers: Int?=0,
    @SerialName("receivedLikes")
    val receivedLikes: Int?=0,
    @SerialName("replies")
    val replies: Int?=0,
    @SerialName("requests")
    val requests: Int?=0,
    @SerialName("responses")
    val responses: Int?=0,
    @SerialName("views")
    val views: Int?=0
)