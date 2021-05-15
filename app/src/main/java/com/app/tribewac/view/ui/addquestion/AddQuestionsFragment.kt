package com.app.tribewac.view.ui.addquestion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.tribewac.R
import com.app.tribewac.databinding.FragmentAddQuestionsBinding
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.viewmodels.AddQuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddQuestionsFragment : Fragment(R.layout.fragment_add_questions), View.OnClickListener {


    private val viewModel: AddQuestionsViewModel by viewModels()
    private val binding by viewBinding<FragmentAddQuestionsBinding>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this
    }

    override fun onClick(view: View?) {

    }

}