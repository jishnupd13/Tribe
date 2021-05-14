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
import com.app.tribewac.R
import com.app.tribewac.databinding.ActivityLoginUserBinding
import com.app.tribewac.utils.getViewBinding
import com.app.tribewac.view.ui.register.RegisterUserActivity
import com.app.tribewac.viewmodels.LoginUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginUserActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: LoginUserViewModel by viewModels()
    private lateinit var binding: ActivityLoginUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding(R.layout.activity_login_user)
        binding.viewModel = viewModel
        binding.listener = this

        val textToSpan: Spannable =
            SpannableString("Don't you have account? Register")
        textToSpan.setSpan(
            ForegroundColorSpan(Color.parseColor("#289238")),
            24,
            32,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textRegister.text = textToSpan
    }

    override fun onClick(view: View?) {
        when(view){
            binding.textRegister->{
                val registerPageIntent= Intent(this,RegisterUserActivity::class.java)
                startActivity(registerPageIntent)
                overridePendingTransition(0,0)
            }
        }
    }
}