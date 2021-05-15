package com.app.tribewac.view.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.ActivityLoginUserBinding
import com.app.tribewac.utils.*
import com.app.tribewac.view.ui.home.HomeActivity
import com.app.tribewac.view.ui.register.RegisterUserActivity
import com.app.tribewac.viewmodels.LoginUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginUserActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: LoginUserViewModel by viewModels()
    private lateinit var binding: ActivityLoginUserBinding

    private lateinit var preferencesHandler: PreferencesHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding(R.layout.activity_login_user)
        binding.viewModel = viewModel
        binding.listener = this

        preferencesHandler = PreferencesHandler(this)

        val textToSpan: Spannable =
            SpannableString("Don't you have account? Register")
        textToSpan.setSpan(
            ForegroundColorSpan(Color.parseColor("#289238")),
            24,
            32,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textRegister.text = textToSpan

        viewModel.getUserTokenData.observe(this, Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.nestedScrollView.show()
                    binding.appLoader.hide()

                    if (it.data != null) {
                        showToast("Login Successful")
                        preferencesHandler.userToken = it.data.accessToken ?: ""
                        preferencesHandler.refreshToken = it.data.refreshToken ?: ""
                        preferencesHandler.userEmail = binding.editTextUserName.text.toString()

                        val homePageIntent = Intent(this, HomeActivity::class.java)
                        startActivity(homePageIntent)
                        finish()
                        overridePendingTransition(0, 0)
                    }

                }

                BaseResult.Status.ERROR -> {
                    binding.nestedScrollView.show()
                    binding.appLoader.hide()
                    showToast("Invalid Email or Password")
                    Timber.tag("login error").e("${it.message}")
                }

                BaseResult.Status.LOADING -> {
                    binding.nestedScrollView.hide()
                    binding.appLoader.show()
                }
            }

        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.textRegister -> {
                val registerPageIntent = Intent(this, RegisterUserActivity::class.java)
                startActivity(registerPageIntent)
                overridePendingTransition(0, 0)
            }

            binding.buttonLogin -> {

                hideKeyboard()
                if (validate()) {
                    viewModel.getUserToken(
                        TRIBE_GRANT_TYPE_PASSWORD,
                        TRIBE_CLIENT_ID,
                        TRIBE_CLIENT_SECRET,
                        binding.editTextUserName.text.toString().trim(),
                        binding.editPassword.text.toString().trim()
                    )
                }
            }
        }
    }

    private fun validate(): Boolean {
        if (!isEmailValid(binding.editTextUserName.text.toString())) {
            showToast("Invalid Email")
            return false
        } else if (binding.editPassword.text.toString().length < 6) {
            showToast("Invalid Password")
            return false
        }
        return true
    }
}