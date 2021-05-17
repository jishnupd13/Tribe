package com.app.tribewac.view.ui.updateprofile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.data.models.updateuser.UpdateUserRequest
import com.app.tribewac.databinding.FragmentUpdateUserBinding
import com.app.tribewac.utils.hide
import com.app.tribewac.utils.show
import com.app.tribewac.utils.showToast
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.viewmodels.UpdateUserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UpdateUserFragment : Fragment(R.layout.fragment_update_user), View.OnClickListener {

    private val viewModel: UpdateUserProfileViewModel by viewModels()
    private val binding by viewBinding<FragmentUpdateUserBinding>()

    private lateinit var preferencesHandler: PreferencesHandler
    private var userId = ""
    private var email = ""
    private var password = ""
    private var userName = ""
    private var location = ""
    private var name = ""

    var gender = ""

    var isUpdate=false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this
        initUi()

        setFragmentResultListener("101") { key, bundle ->
            if (!bundle.isEmpty) {
                gender = bundle.getString("gender", "")
                binding.editTextGender.setText(gender)
                isUpdate=true
            }
        }



    }


    private fun initUi() {

        preferencesHandler = PreferencesHandler(requireContext())
        userId = preferencesHandler.userId
        email = preferencesHandler.userEmail
        password = preferencesHandler.userPassword


        viewModel.getUpdateUserData?.observe(viewLifecycleOwner, Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()
                    requireActivity().showToast("Profile updated successfully")
                    findNavController().navigateUp()

                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()
                    Timber.tag("error").e("${it.message}")
                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.nestedScroll.hide()
                }
            }

        })





        viewModel.getSpecificUserDetailsData?.observe(viewLifecycleOwner, Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    if(!isUpdate){
                        if (it.data != null) {

                            location = it.data.profile?.location ?: ""
                            userName = it.data.profile?.username ?: ""
                            name = it.data.profile?.name ?: ""
                            gender = it.data.profile?.gender?:""

                            binding.editTextName.setText(it.data.profile?.name)
                            binding.editTextEmail.setText(email)
                            binding.editTextGender.setText(it.data.profile?.gender)
                            binding.editTextLocation.setText(it.data.profile?.location)
                            binding.editTextUserName.setText(it.data.profile?.username)
                            binding.editTextPassword.setText(password)
                        }
                    }


                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    Timber.tag("error").e("${it.message}")
                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.nestedScroll.hide()
                }
            }

        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imageBack -> {
                findNavController().navigateUp()
            }

            binding.editTextGender -> {
                findNavController().navigate(R.id.action_updateUserFragment_to_selectGenderFragment)
            }

            binding.btnRegister -> {
                viewModel.getUpdateUser(
                    userId,
                    UpdateUserRequest(username = userName,email = email,location = location,gender = gender,password = password,name = name)
                )
            }
        }
    }
}