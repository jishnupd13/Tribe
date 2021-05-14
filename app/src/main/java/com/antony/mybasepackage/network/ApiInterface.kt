package com.antony.mybasepackage.network

import com.antony.mybasepackage.data.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Tag

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiInterface {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    suspend fun getPosts(
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.NONE
    ): Response<List<Post>>

    @POST("/posts")
    suspend fun getList(
        @Body postRequest: Post
    ): Response<List<Post>>
}