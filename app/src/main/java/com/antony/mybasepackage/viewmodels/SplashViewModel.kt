package com.antony.mybasepackage.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.antony.mybasepackage.data.repository.AppRepository
import javax.inject.Inject

class SplashViewModel @ViewModelInject constructor(private val repository: AppRepository) : ViewModel() {
}