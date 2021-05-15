package com.app.tribewac.network

import com.app.tribewac.data.models.Post
import com.app.tribewac.data.models.getmaintoken.GetAppMainTokenModel
import com.app.tribewac.data.models.registeruser.RegisterUserResponseModel
import retrofit2.Response
import retrofit2.http.*

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

    /** tribe get origin token */
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getUserTokenUsingEmail(
        @Field("grant_type") grantType:String,
        @Field("client_id") clientId:String,
        @Field("client_secret") clientSecret:String,
        @Field("email") email:String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.NONE
    ):Response<GetAppMainTokenModel>

    /** tribe create a user */
    @FormUrlEncoded
    @POST("users")
    suspend fun getTokenUserRegister(
        @Field("username") username:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("confirmPassword") confirmPassword:String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.ADMIN_TOKEN
    ):Response<RegisterUserResponseModel>


    /** tribe get user token */
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getUserTokenUsingEmailAndPassword(
        @Field("grant_type") grantType:String,
        @Field("client_id") clientId:String,
        @Field("client_secret") clientSecret:String,
        @Field("username") email:String,
        @Field("password") password:String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.ADMIN_TOKEN
    ):Response<GetAppMainTokenModel>


}