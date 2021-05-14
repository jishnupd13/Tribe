package com.antony.mybasepackage.injection

import com.antony.mybasepackage.data.local.PreferencesHandler
import com.antony.mybasepackage.network.ApiInterface
import com.antony.mybasepackage.network.AuthorizationInterceptor
import com.antony.mybasepackage.utils.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    /*
     * The method returns the Okhttp object
     * */
    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        preferencesHandler: PreferencesHandler
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().apply {
            addInterceptor(AuthorizationInterceptor(preferencesHandler))
            addInterceptor(logging)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }


    /*
     * The method returns the Retrofit object
     * */
    @ExperimentalSerializationApi
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory(contentType)
            )
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }


    /*
     * We need the MovieApiService module.
     * For this, We need the Retrofit object, Gson, Cache and OkHttpClient .
     * So we will define the providers for these objects here in this module.
     *
     * */
    @Provides
    @Singleton
    internal fun provideMovieApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}