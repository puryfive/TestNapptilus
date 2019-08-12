package com.oriol.oompasmanager.presentation.ompadetail

import androidx.lifecycle.MutableLiveData
import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.domain.usecase.GetOmpaDetailUseCase
import com.oriol.oompasmanager.presentation.BaseViewModel

class OmpaDetailViewModel constructor(private val getOmpaDetailUseCase: GetOmpaDetailUseCase): BaseViewModel() {

    val ompaDetailLive = MutableLiveData<OmpaDetail>()

    fun fetchOmpaDetail(ompaId:Int) {
        empty.value = true
        dataLoading.value = true
        getOmpaDetailUseCase.getOmpaDetail(ompaId) { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                ompaDetailLive.value =  response
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}