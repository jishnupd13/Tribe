package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.app.tribewac.data.repository.AppRepository

class SplashViewModel @ViewModelInject constructor(private val repository: AppRepository) : ViewModel() {
}