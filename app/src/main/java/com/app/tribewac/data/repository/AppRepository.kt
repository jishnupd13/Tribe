package com.app.tribewac.data.repository

import com.app.tribewac.base.safeApiCall
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.data.models.Post
import com.app.tribewac.network.ApiInterface
import dagger.Reusable
import javax.inject.Inject

/**
 * Created By antony on 6/4/2019.
 */
@Reusable
class AppRepository @Inject constructor(
    private val webService: ApiInterface,
//    private val appDao: AppDao,
    private val preferencesHandler: PreferencesHandler
) {

    suspend fun getPosts() = safeApiCall { webService.getPosts() }

    suspend fun getList(postRequest: Post) = safeApiCall { webService.getList(postRequest) }

    fun saveUserToken(token: String) {
        preferencesHandler.userToken = token
    }

}