package com.antony.mybasepackage.data.repository

import com.antony.mybasepackage.base.safeApiCall
import com.antony.mybasepackage.data.local.PreferencesHandler
import com.antony.mybasepackage.data.models.Post
import com.antony.mybasepackage.network.ApiInterface
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