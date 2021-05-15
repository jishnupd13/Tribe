package com.app.tribewac.view.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.tribewac.R
import com.app.tribewac.databinding.FragmentAccountBinding
import com.app.tribewac.databinding.FragmentAddQuestionsBinding
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.viewmodels.AccountViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account), View.OnClickListener {

    private val viewModel: AccountViewModel by viewModels()
    private val binding by viewBinding<FragmentAccountBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.listener = this
    }

    override fun onClick(view: View?) {

    }

}