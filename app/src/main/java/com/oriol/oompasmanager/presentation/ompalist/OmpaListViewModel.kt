package com.oriol.oompasmanager.presentation.ompalist

import androidx.lifecycle.MutableLiveData
import com.oriol.oompasmanager.domain.model.ResultsItem
import com.oriol.oompasmanager.domain.usecase.GetOmpaListUseCase
import com.oriol.oompasmanager.presentation.BaseViewModel

class OmpaListViewModel constructor(private val getOmpaListUseCase: GetOmpaListUseCase): BaseViewModel() {

    val ompaListLive = MutableLiveData<List<ResultsItem?>>()

    fun fetchOmpaList() {
        dataLoading.value = true
        getOmpaListUseCase.getOmpasList { isSuccess, response ->
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
