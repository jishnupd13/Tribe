package com.app.tribewac.view.ui.addquestion

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.FragmentAddQuestionsBinding
import com.app.tribewac.utils.*
import com.app.tribewac.viewmodels.AddQuestionsViewModel
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File

@AndroidEntryPoint
class AddQuestionsFragment : Fragment(R.layout.fragment_add_questions), View.OnClickListener {


    private val viewModel: AddQuestionsViewModel by viewModels()
    private val binding by viewBinding<FragmentAddQuestionsBinding>()

    private var resultUri: Uri? = null
    private lateinit var part: MultipartBody.Part

    private var uploadedImageUrl = ""
    private lateinit var preferencesHandler: PreferencesHandler
    var imageArrayList = arrayListOf<String>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this


        preferencesHandler = PreferencesHandler(requireContext())


        viewModel.getUploadQuestionImageUrl.observe(requireActivity(), Observer {
            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    if (it.data?.uploaded == true) {
                        uploadedImageUrl = it.data.url ?: ""
                    }

                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()
                    Timber.tag("error occured").e("${it.message}")
                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.nestedScroll.hide()
                }
            }

        })


        viewModel.createQuestionData.observe(requireActivity(), Observer {

            when (it.status) {


                BaseResult.Status.SUCCESS -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    if (it.data!=null) {

                        requireActivity().showToast("Question Created Successfully")
                        binding.editTitle.setText("")
                        binding.textTopics.setText("")
                        binding.imageCamera.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_add_photo
                            )
                        )
                        resultUri = null
                        uploadedImageUrl = ""
                        imageArrayList = arrayListOf()
                        binding.uploadedImage.hide()

                    }
                }

                BaseResult.Status.ERROR -> {
                    binding.appLoader.hide()
                    binding.nestedScroll.show()
                    Timber.tag("error occured").e("${it.message}")

                }

                BaseResult.Status.LOADING -> {
                    binding.appLoader.show()
                    binding.nestedScroll.hide()
                }
            }


        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                resultUri = result.uri

                binding.uploadedImage.visibility = View.VISIBLE
                binding.uploadedImage.setImageURI(resultUri)


                val file = File(resultUri?.path!!)
                val fbody: RequestBody = RequestBody.create(
                    "image/*".toMediaTypeOrNull()!!,
                    file
                )
                part = MultipartBody.Part.createFormData("file", file.name, fbody)

                viewModel.getUpdateUserProfileImage(part)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }

        }
    }

    override fun onClick(view: View?) {

        when (view) {
            binding.btnPost -> {

                if (validate()) {
                    imageArrayList = arrayListOf<String>()
                    imageArrayList.add(uploadedImageUrl)
                    viewModel.createQuestionData(
                        binding.editTitle.text.toString().trim(),
                        binding.textTopics.text.toString().trim(),
                        preferencesHandler.userId,
                        imageArrayList
                    )
                }
            }

            binding.layoutAddImage -> {
                CropImage.activity().start(this.requireContext(), this)
            }

            binding.layoutToolBar -> {
                hideKeyboard()
            }

        }
    }

    private fun validate(): Boolean {
        if (binding.textTopics.text.toString().trim() == "")
            return false
        else if (binding.editTitle.text.toString().trim() == "")
            return false
        return true
    }

}