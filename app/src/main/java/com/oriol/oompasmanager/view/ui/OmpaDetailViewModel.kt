package com.oriol.oompasmanager.view.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oriol.oompasmanager.model.ApiRepository
import com.oriol.oompasmanager.model.entities.OmpaDetail

class OmpaDetailViewModel : ViewModel() {

    val ompaDetailLive = MutableLiveData<OmpaDetail>()

    fun fetchOmpaDetail(id:Int) {
        ApiRepository.getInstance().getOmpaDetail(id) { isSuccess, response ->
            if (isSuccess) {
                ompaDetailLive.value =  response
            } else {
                println("Error getting data")
            }
        }
    }
}