package com.oriol.oompasmanager.data.repository

import com.oriol.oompasmanager.data.datasource.GetOmpaDetailDataSource
import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.domain.repository.GetOmpaDetailRepository

class GetOmpaDetailRepositoryImpl constructor(
    private val remoteDataSource: GetOmpaDetailDataSource
) : GetOmpaDetailRepository {

    override fun getOmpaDetail(ompaId: Int, onResult: (isSuccess: Boolean, ompasList: OmpaDetail?) -> Unit) {
        remoteDataSource.getOmpaDetail(ompaId,onResult)
    }
}