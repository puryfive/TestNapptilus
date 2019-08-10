package com.oriol.oompasmanager.domain.usecase

import com.oriol.oompasmanager.datasource.model.ResponseApi
import com.oriol.oompasmanager.domain.repository.GetOmpaListRepository
import com.oriol.oompasmanager.domain.model.ResultsItem

class GetOmpaListUseCase constructor(
    private val getOmpaListRepository: GetOmpaListRepository
) {

    fun getOmpasList(onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit){
        return getOmpaListRepository.getOmpaList(onResult)
    }
}

