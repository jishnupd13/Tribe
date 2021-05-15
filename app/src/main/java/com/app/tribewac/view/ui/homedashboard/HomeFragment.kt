package com.app.tribewac.view.ui.homedashboard

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.models.questionlists.QuestionListModelResponseItem
import com.app.tribewac.databinding.FragmentHomeBinding
import com.app.tribewac.utils.hide
import com.app.tribewac.utils.show
import com.app.tribewac.utils.viewBinding
import com.app.tribewac.view.adapters.questions.QuestionAdapter
import com.app.tribewac.view.listeners.AdapterClickListener
import com.app.tribewac.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var questionAdapter: QuestionAdapter
    private var questionList=ArrayList<QuestionListModelResponseItem>()

    private val questionClickListener =
        object : AdapterClickListener<QuestionListModelResponseItem> {
            override fun onItemClicked(obj: QuestionListModelResponseItem, position: Int) {

                val bundle= bundleOf(
                    "questionId" to obj.id
                )

                findNavController().navigate(R.id.action_homeFragment_to_questionDetailsFragment,bundle)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.listener = this
        initViews()
    }

    private fun initViews() {
        questionAdapter = QuestionAdapter(questionClickListener,requireContext())
        binding.recyclerView.adapter = questionAdapter

        viewModel.getAllQuestions()
        viewModel.getQuestionsList.observe(requireActivity(), Observer {

            when(it.status){

                BaseResult.Status.SUCCESS->{
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    if(it.data!=null){
                        questionList= arrayListOf()
                        questionList.addAll(it.data)
                        questionAdapter.submitList(questionList)
                    }

                }

                BaseResult.Status.ERROR->{
                    binding.appLoader.hide()
                    binding.nestedScroll.show()

                    Timber.tag("errorsssss").e("${it.message}")
                }

                BaseResult.Status.LOADING->{
                    binding.appLoader.show()
                    binding.nestedScroll.hide()
                }
            }

        })
    }

    override fun onClick(v: View?) {
    }

}