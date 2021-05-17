package com.app.tribewac.network

import com.app.tribewac.data.models.Post
import com.app.tribewac.data.models.answersmodel.AnswersModelResponseItem
import com.app.tribewac.data.models.createanswermodel.CreatedAnswerModel
import com.app.tribewac.data.models.getmaintoken.GetAppMainTokenModel
import com.app.tribewac.data.models.questionimageupload.QuestionImageUploadModelResponse
import com.app.tribewac.data.models.questionlists.QuestionListModelResponseItem
import com.app.tribewac.data.models.questionresponsemodel.QuestionResponseModelResponse
import com.app.tribewac.data.models.registeruser.RegisterUserResponseModel
import com.app.tribewac.data.models.specificuser.GetSpecificUserModel
import com.app.tribewac.data.models.updateuser.UpdateUserRequest
import com.app.tribewac.data.models.updateuser.UpdateUserResponse
import com.app.tribewac.data.models.userinformation.UserInformationResponseModelItem
import okhttp3.MultipartBody
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
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("email") email: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.NONE
    ): Response<GetAppMainTokenModel>

    /** tribe create a user */
    @FormUrlEncoded
    @POST("users")
    suspend fun getTokenUserRegister(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.ADMIN_TOKEN
    ): Response<RegisterUserResponseModel>


    /** tribe get user token */
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun getUserTokenUsingEmailAndPassword(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("username") email: String,
        @Field("password") password: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.ADMIN_TOKEN
    ): Response<GetAppMainTokenModel>

    @GET("users")
    suspend fun getUserDetails(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
        @Query("email") email: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<List<UserInformationResponseModelItem>>


    @Multipart
    @POST("uploads/questions")
    suspend fun getImageUploadUrl(
        @Part file: MultipartBody.Part,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<QuestionImageUploadModelResponse>


    /** function to create a question*/
    @FormUrlEncoded
    @POST("questions")
    suspend fun createQuestion(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("anonymous") anonymous: Boolean,
        @Field("from") from: String,
        @Field("images[]") images: List<String>,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<QuestionResponseModelResponse>


    @GET("questions")
    suspend fun getQuestionList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<List<QuestionListModelResponseItem>>

    @GET("questions/{id}/")
    suspend fun getQuestionDetails(@Path("id") id: String): Response<QuestionListModelResponseItem>

    @POST("questions/{id}/followers")
    suspend fun getQuestionLike(
        @Path("id") id: String, @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<QuestionListModelResponseItem>

    @DELETE("questions/{id}/followers")
    suspend fun getQuestionUnLike(
        @Path("id") id: String, @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<QuestionListModelResponseItem>

    @FormUrlEncoded
    @POST("questions/{id}/answers")
    suspend fun createAnswer(
        @Path("id") id: String,
        @Field("content") content: String,
        @Field("anonymous") anonymous: Boolean,
        @Field("status") status: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<CreatedAnswerModel>


    @GET("questions/{id}/answers")
    suspend fun getAnswersOfQuestion(
        @Path("id") id: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("sort") sort: String,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<List<AnswersModelResponseItem>>

    @GET("users/{id}/")
    suspend fun getSpecificUserDetails(@Path("id") userId: String): Response<GetSpecificUserModel>

    @PUT("users/{id}")
    suspend fun getUpdateUser(
        @Path("id") id: String,
        @Body body:UpdateUserRequest,
        @Tag authorization: AuthorizationInterceptor.AuthorizationType
        = AuthorizationInterceptor.AuthorizationType.USER_TOKEN
    ): Response<UpdateUserResponse>

}