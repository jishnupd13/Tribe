package com.app.tribewac.view.ui.splashtribe

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.ActivitySplashTribeBinding
import com.app.tribewac.utils.*
import com.app.tribewac.view.ui.home.HomeActivity
import com.app.tribewac.view.ui.login.LoginUserActivity
import com.app.tribewac.viewmodels.SplashTribeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashTribeActivity : AppCompatActivity() {

    private val viewModel: SplashTribeViewModel by viewModels()
    private lateinit var binding: ActivitySplashTribeBinding

    private lateinit var preferencesHandler: PreferencesHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_tribe)
        binding = getViewBinding(R.layout.activity_splash_tribe)
        binding.viewModel = viewModel
        preferencesHandler = PreferencesHandler(this)

        if (preferencesHandler.adminToken != "") {
            lifecycleScope.launch {
                delay(1500)
                Intent(applicationContext, HomeActivity::class.java)
                    .also { startActivity(it); finish();overridePendingTransition(0, 0) }
            }
        } else {
            viewModel.getUserToken(
                TRIBE_GRANT_TYPE, TRIBE_CLIENT_ID, TRIBE_CLIENT_SECRET,
                TRIBE_ADMIN_MAIL
            )
        }



        viewModel.getUserTokenData.observe(this, Observer {
            when (it.status) {
                BaseResult.Status.SUCCESS -> {

                    if (it.data != null) {
                        preferencesHandler.adminToken = it.data.accessToken ?: ""
                        Intent(applicationContext, LoginUserActivity::class.java)
                            .also { startActivity(it); finish();overridePendingTransition(0, 0) }
                    }
                }

                BaseResult.Status.ERROR -> {

                }
                else -> {
                }
            }
        })
    }
}