package com.oriol.oompasmanager.data.repository

import com.oriol.oompasmanager.data.datasource.GetOmpaListDataSource
import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.datasource.model.ResponseApi
import com.oriol.oompasmanager.domain.model.ResultsItem
import com.oriol.oompasmanager.domain.repository.GetOmpaListRepository

class GetOmpaListRepositoryImpl constructor(
    private val remoteDataSource: GetOmpaListDataSource
) : GetOmpaListRepository {

    override fun getOmpaList(onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit){
        remoteDataSource.getOmpaList(onResult)
    }
}