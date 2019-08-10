package com.oriol.oompasmanager.view.ui

import androidx.lifecycle.MutableLiveData
import com.oriol.oompasmanager.model.ApiRepository
import com.oriol.oompasmanager.model.entities.ResultsItem

class OmpaListViewModel: BaseViewModel() {

    val ompaListLive = MutableLiveData<List<ResultsItem?>>()

    fun fetchOmpaList() {
        dataLoading.value = true
        ApiRepository.getInstance().getOmpasList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                ompaListLive.value =  response?.results
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}
