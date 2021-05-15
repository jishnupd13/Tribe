package com.app.tribewac.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.tribewac.R
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.local.PreferencesHandler
import com.app.tribewac.databinding.ActivityHomeBinding
import com.app.tribewac.utils.getViewBinding
import com.app.tribewac.utils.visibleOnCondition
import com.app.tribewac.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    private lateinit var preferencesHandler: PreferencesHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding(R.layout.activity_home)
        binding.viewModel = viewModel
        preferencesHandler= PreferencesHandler(this)

        val topLevelDest = setOf(R.id.homeFragment, R.id.addQuestionsFragment, R.id.accountFragment)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.activity_main_nav_host_fragment) as NavHostFragment)
                .navController
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.visibleOnCondition(destination.id in topLevelDest)
        }


        if(preferencesHandler.userId==""){
            viewModel.getUserToken(preferencesHandler.userEmail)
        }


        viewModel.getUserInformationLiveData.observe(this, Observer {

            when(it.status){

                BaseResult.Status.SUCCESS->{
                    binding.appLoader.visibility=View.GONE

                    if(it.data?.size?:0>0){
                        preferencesHandler.userId= it.data?.get(0)?.id ?:""
                        Timber.tag("userId").e(it.data?.get(0)?.id ?:"")
                    }

                }

                BaseResult.Status.ERROR->{
                    binding.appLoader.visibility=View.GONE
                    Timber.tag("message").e("${it.message}")

                }

                BaseResult.Status.LOADING->{
                    binding.appLoader.visibility=View.VISIBLE
                }
            }
        })
    }
}