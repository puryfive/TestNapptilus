package com.oriol.oompasmanager.domain.usecase

import com.oriol.oompasmanager.datasource.model.ResponseApi
import com.oriol.oompasmanager.domain.repository.GetOmpaListRepository

class GetOmpaListUseCase constructor(
    private val getOmpaListRepository: GetOmpaListRepository
) {

    fun getOmpasList(page:Int,onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit){
        return getOmpaListRepository.getOmpaList(page, onResult)
    }
}

