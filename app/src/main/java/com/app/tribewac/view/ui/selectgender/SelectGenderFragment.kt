package com.app.tribewac.view.ui.selectgender

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.models.selectgender.Gender
import com.app.tribewac.databinding.FragmentSelectGenderBinding
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.view.adapters.gender.GenderAdapter
import com.app.tribewac.view.listeners.AdapterClickListener
import com.app.tribewac.viewmodels.SelectGenderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectGenderFragment : Fragment(R.layout.fragment_select_gender), View.OnClickListener {


    private val viewModel: SelectGenderViewModel by viewModels()
    private val binding by viewBinding<FragmentSelectGenderBinding>()

    private lateinit var genderAdapter: GenderAdapter
    private val genderClickListener =
        object : AdapterClickListener<Gender> {
            override fun onItemClicked(obj: Gender, position: Int) {

                val bundle = bundleOf("gender" to obj.gender)
                setFragmentResult("101", bundle)
                findNavController().navigateUp()

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this
        genderAdapter = GenderAdapter(genderClickListener)
        binding.recyclerViewGender.adapter = genderAdapter


        viewModel.genderListData.observe(viewLifecycleOwner, Observer {

            if (it.status == BaseResult.Status.SUCCESS) {
                if (it.data?.size ?: 0 > 0) {
                    genderAdapter.submitList(it.data!!)
                }
            }
        })

    }

    override fun onClick(view: View?) {

        when (view) {
            binding.imageBack -> {
                findNavController().navigateUp()
            }
        }

    }

}