package com.app.tribewac.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.app.tribewac.utils.SHARED_PREFERENCES_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created By antony on 6/6/2019.
 */
@Singleton
class PreferencesHandler @Inject
internal constructor(@ApplicationContext private val mContext: Context) {

    private val sharedPreference: SharedPreferences
        get() = mContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    var userToken: String
        get() = sharedPreference.getString("token", "") ?: ""
        set(value) = sharedPreference.edit { putString("token", value) }

    var adminToken: String
        get() = sharedPreference.getString("adminToken", "") ?: ""
        set(value) = sharedPreference.edit { putString("adminToken", value) }

    var refreshToken: String
        get() = sharedPreference.getString("refreshToken", "") ?: ""
        set(value) = sharedPreference.edit { putString("refreshToken", value) }

}