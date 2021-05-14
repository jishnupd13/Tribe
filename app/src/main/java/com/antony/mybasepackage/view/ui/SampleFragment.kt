package com.antony.mybasepackage.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.antony.mybasepackage.R
import com.antony.mybasepackage.databinding.FragmentSampleBinding
import com.antony.mybasepackage.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * Sample class to demonstrate the use of fragment.
 * Remove this class once this is no longer needed
 */

@AndroidEntryPoint
class SampleFragment : Fragment(R.layout.fragment_sample) {

    private val binding by viewBinding<FragmentSampleBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // rest of the code
    }
}