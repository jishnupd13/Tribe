package com.app.tribewac.view.ui.register

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.databinding.ActivityRegisterUserBinding
import com.app.tribewac.utils.*
import com.app.tribewac.viewmodels.RegisterUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterUserActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: RegisterUserViewModel by viewModels()
    private lateinit var binding: ActivityRegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        binding = getViewBinding(R.layout.activity_register_user)
        binding.viewModel = viewModel
        binding.listener = this

        viewModel.registerUserData.observe(this, Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.nestedScrollView.show()
                    binding.appLoader.hide()

                    if (it.data != null) {
                        if (it.data.userId != "") {
                            showToast("Registration Successful")
                            onBackPressed()
                        }
                    }
                }

                BaseResult.Status.LOADING -> {
                    binding.nestedScrollView.hide()
                    binding.appLoader.show()
                }
                BaseResult.Status.ERROR -> {
                    binding.nestedScrollView.show()
                    binding.appLoader.hide()
                }
            }

        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imageBack -> {
                onBackPressed()
            }

            binding.btnRegister -> {
                hideKeyboard()
                if (validate()) {
                    viewModel.registerUser(
                        binding.editTextUserName.text.toString().trim(),
                        binding.editTextName.text.toString().trim(),
                        binding.editTextEmail.text.toString().trim(),
                        binding.editTextPassword.text.toString().trim(),
                        binding.editTextConfirmPassword.text.toString().trim()
                    )
                } else {
                    showToast("Validation Incorrect")
                }


            }
        }
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }

    private fun validate(): Boolean {

        if (binding.editTextPassword.text.toString()
                .trim() != binding.editTextConfirmPassword.text.toString().trim()
        )
            return false
        return true
    }
}