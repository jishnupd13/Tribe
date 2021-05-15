package com.app.tribewac.view.ui.questiondetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.FragmentQuestionDetailsBinding
import com.app.tribewac.utils.hide
import com.app.tribewac.utils.show
import com.app.tribewac.utils.showToast
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.viewmodels.QuestionDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class QuestionDetailsFragment : Fragment(R.layout.fragment_question_details), View.OnClickListener {


    private val viewModel: QuestionDetailsViewModel by viewModels()
    private val binding by viewBinding<FragmentQuestionDetailsBinding>()

    private var questionId = ""
    private var likeCount = 0
    private var answerCount = 0
    private lateinit var preferencesHandler: PreferencesHandler

    private var userId = ""


    private var isLike = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this

        preferencesHandler = PreferencesHandler(requireContext())

        questionId = requireArguments().getString("questionId", "")
        userId = preferencesHandler.userId

        if (questionId != "")
            viewModel.getQuestionDetails(questionId)


        viewModel.createAnswerUsingQuestion.observe(requireActivity(), Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {

                    binding.nestedScroll.show()
                    binding.appLoader.hide()

                    if(it.data!=null){
                        answerCount += 1
                        binding.textCount.text = "$likeCount Likes and $answerCount Answers"
                        requireActivity().showToast("You Created the Answer Successfully")
                    }

                }

                BaseResult.Status.LOADING -> {
                    binding.nestedScroll.hide()
                    binding.appLoader.show()
                }

                BaseResult.Status.ERROR -> {
                    binding.nestedScroll.show()
                    binding.appLoader.hide()

                    Timber.tag("error").e("${it.message}")
                }
            }

        })



        viewModel.getLikeQuestionData.observe(requireActivity(), Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {

                    if (it.data != null) {
                        isLike = true
                        binding.textLike.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_like,
                            0,
                            0,
                            0
                        )
                        likeCount += 1
                        binding.textCount.text = "$likeCount Likes and $answerCount Answers"

                    }

                }

                BaseResult.Status.LOADING -> {

                }

                BaseResult.Status.ERROR -> {

                    Timber.tag("error").e("${it.message}")
                }
            }

        })


        viewModel.getUnlikeQuestionData.observe(requireActivity(), Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {

                    if (it.data != null) {
                        isLike = false
                        binding.textLike.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_un_like,
                            0,
                            0,
                            0
                        )
                        likeCount -= 1
                        binding.textCount.text = "$likeCount Likes and $answerCount Answers"

                    }

                }

                BaseResult.Status.LOADING -> {

                }

                BaseResult.Status.ERROR -> {

                    Timber.tag("error").e("${it.message}")
                }
            }

        })


        viewModel.getQuestionDetails.observe(requireActivity(), Observer {

            when (it.status) {

                BaseResult.Status.SUCCESS -> {
                    binding.nestedScroll.show()
                    binding.appLoader.hide()

                    if (it.data != null) {
                        binding.textQuestionTitle.text = it.data.title
                        binding.textDescription.text = it.data.description
                        likeCount = it.data.counts?.followers ?: 0
                        answerCount = it.data.counts?.answers ?: 0
                        binding.textCount.text = "$likeCount Likes and $answerCount Answers"

                        if (it.data.images?.size ?: 0 == 0) {
                            binding.imageQuestion.hide()
                        } else {
                            binding.imageQuestion.show()
                            Glide.with(requireContext())
                                .load(it.data.images?.get(0)?.src ?: "")
                                .placeholder(R.drawable.placeholder)
                                .timeout(60000)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .error(R.drawable.placeholder)
                                .into(binding.imageQuestion)
                        }

                        if (it.data.followers?.contains(userId) == true) {
                            binding.textLike.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_like,
                                0,
                                0,
                                0
                            )
                            isLike = true
                        } else {
                            binding.textLike.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_un_like,
                                0,
                                0,
                                0
                            )
                            isLike = false
                        }
                    }

                }

                BaseResult.Status.ERROR -> {
                    binding.nestedScroll.show()
                    binding.appLoader.hide()

                    Timber.tag("error").e("${it.message}")
                }

                BaseResult.Status.LOADING -> {
                    binding.nestedScroll.hide()
                    binding.appLoader.show()
                }
            }

        })

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imageBack -> {
                findNavController().navigateUp()
            }

            binding.textLike -> {
                if (!isLike) {
                    viewModel.getLikeQuestion(questionId)
                } else {
                    viewModel.getUnLikeQuestion(questionId)
                }
            }

            binding.textAnswers -> {
                showCreateAnswerDialog()
            }
        }
    }


    private fun showCreateAnswerDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("")
        builder.setCancelable(true)
        val customLayout: View = layoutInflater.inflate(R.layout.item_create_answer, null)
        val textAnswer = customLayout.findViewById<TextView>(R.id.text_answer)
        val buttonCreateAnswer = customLayout.findViewById<MaterialButton>(R.id.btn_create_answer)
        builder.setView(customLayout)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        buttonCreateAnswer.setOnClickListener {
            dialog.dismiss()
            viewModel.createAnswerUsingQuestions(questionId, textAnswer.text.toString().trim())
        }

    }


}