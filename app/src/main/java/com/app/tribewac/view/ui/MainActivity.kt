package com.app.tribewac.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.models.Post
import com.app.tribewac.databinding.ActivityMainBinding
import com.app.tribewac.utils.InternetUtils
import com.app.tribewac.utils.getViewBinding
import com.app.tribewac.utils.showToast
import com.app.tribewac.view.adapters.PostListAdapter
import com.app.tribewac.view.listeners.AdapterClickListener
import com.app.tribewac.viewmodels.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint

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
