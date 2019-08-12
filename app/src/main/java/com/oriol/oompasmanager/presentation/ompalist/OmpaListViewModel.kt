package com.oriol.oompasmanager.presentation.ompalist

import androidx.lifecycle.MutableLiveData
import com.oriol.oompasmanager.datasource.model.ResponseApi
import com.oriol.oompasmanager.domain.usecase.GetOmpaListUseCase
import com.oriol.oompasmanager.presentation.BaseViewModel

class OmpaListViewModel constructor(private val getOmpaListUseCase: GetOmpaListUseCase): BaseViewModel() {

    val ompaListLive = MutableLiveData<ResponseApi>()

    fun fetchOmpaList(page:Int) {
        dataLoading.value = true
        getOmpaListUseCase.getOmpasList(page) { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                ompaListLive.value =  response
                empty.value = false
            } else {
                empty.value = ompaListLive.value?.results.isNullOrEmpty()
            }
        }
    }
}
