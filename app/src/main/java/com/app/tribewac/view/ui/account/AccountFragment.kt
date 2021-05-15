package com.app.tribewac.view.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.tribewac.R
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.FragmentAccountBinding
import com.app.tribewac.utils.showToast
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.view.ui.login.LoginUserActivity
import com.app.tribewac.viewmodels.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account), View.OnClickListener {

    private val viewModel: AccountViewModel by viewModels()
    private val binding by viewBinding<FragmentAccountBinding>()

    private lateinit var preferencesHandler: PreferencesHandler


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.listener = this

        preferencesHandler = PreferencesHandler(requireContext())
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.textLogOut -> {
                preferencesHandler.userToken = ""
                preferencesHandler.userEmail = ""
                preferencesHandler.userId = ""

                val loginIntent = Intent(requireContext(), LoginUserActivity::class.java)
                startActivity(loginIntent)
                requireActivity().finish()
                requireActivity().overridePendingTransition(0, 0)
                requireActivity().showToast("SignOut Successfully")
            }
        }
    }

}