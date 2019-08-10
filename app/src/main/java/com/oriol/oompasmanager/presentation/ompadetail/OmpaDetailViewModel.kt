package com.oriol.oompasmanager.presentation.ompadetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.domain.usecase.GetOmpaDetailUseCase

class OmpaDetailViewModel constructor(private val getOmpaDetailUseCase: GetOmpaDetailUseCase): ViewModel() {

    val ompaDetailLive = MutableLiveData<OmpaDetail>()

    fun fetchOmpaDetail(ompaId:Int) {
        getOmpaDetailUseCase.getOmpaDetail(ompaId) { isSuccess, response ->
            if (isSuccess) {
                ompaDetailLive.value =  response
            } else {
                println("Error getting data")
            }
        }
    }
}