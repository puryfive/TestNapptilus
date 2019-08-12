package com.oriol.oompasmanager.domain.usecase

import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.domain.repository.GetOmpaDetailRepository

class GetOmpaDetailUseCase constructor(
    private val getOmpaDetailRepository: GetOmpaDetailRepository
) {

    fun getOmpaDetail(ompaId: Int, onResult: (isSuccess: Boolean, ompasList: OmpaDetail?) -> Unit){
        return getOmpaDetailRepository.getOmpaDetail(ompaId, onResult)
    }
}