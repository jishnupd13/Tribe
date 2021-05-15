package com.app.tribewac.view.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.tribewac.R
import com.app.tribewac.databinding.ActivityHomeBinding
import com.app.tribewac.utils.getViewBinding
import com.app.tribewac.utils.visibleOnCondition
import com.app.tribewac.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding(R.layout.activity_home)
        binding.viewModel = viewModel

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
    }
}