package com.app.tribewac.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tribewac.base.BaseResult
import com.app.tribewac.data.models.selectgender.Gender
import com.app.tribewac.data.repository.AppRepository

class SelectGenderViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {



    var genderListData: MutableLiveData<BaseResult<List<Gender>>> =
        MutableLiveData()

    init {
        setGenderData()
    }


    private fun setGenderData() {
        val genderList = ArrayList<Gender>()
        genderList.add(Gender(1, "Male"))
        genderList.add(Gender(2, "FeMale"))
        genderListData.postValue(BaseResult(BaseResult.Status.SUCCESS, genderList, ""))
    }
}