package com.antony.mybasepackage.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.antony.mybasepackage.R
import com.antony.mybasepackage.base.BaseResult
import com.antony.mybasepackage.data.models.Post
import com.antony.mybasepackage.databinding.ActivityMainBinding
import com.antony.mybasepackage.utils.InternetUtils
import com.antony.mybasepackage.utils.getViewBinding
import com.antony.mybasepackage.utils.showToast
import com.antony.mybasepackage.view.adapters.PostListAdapter
import com.antony.mybasepackage.view.listeners.AdapterClickListener
import com.antony.mybasepackage.viewmodels.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PostListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { PostListAdapter(itemClickListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(R.layout.activity_main)
        binding.main = viewModel

        initViews()
        observePosts()
    }


    private fun initViews() {
        binding.postList.adapter = adapter
        showToast("${InternetUtils.isInternetAvailable}")
    }

    private fun observePosts() {
        viewModel.sample.observe(this) {
            binding.isLoading = false
            when (it.status) {
                BaseResult.Status.SUCCESS -> {
                    it.data?.let(adapter::submitList)
                }
                BaseResult.Status.ERROR -> {
                    showToast(it.message ?: "")
                }
                BaseResult.Status.LOADING -> {
                    binding.isLoading = true
                }
            }
        }
    }

    private val itemClickListener = object : AdapterClickListener<Post> {
        override fun onItemClicked(obj: Post, position: Int) {
            obj.title.let(this@MainActivity::showToast)
        }
    }
}
