package com.app.tribewac.view.ui.homedashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.tribewac.R
import com.app.tribewac.databinding.FragmentHomeBinding
import com.app.tribewac.databinding.FragmentSampleBinding
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),View.OnClickListener {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=viewModel
        binding.listener=this
    }

    override fun onClick(v: View?) {
    }

}