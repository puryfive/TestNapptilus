package com.oriol.oompasmanager.domain.repository

import com.oriol.oompasmanager.datasource.model.ResponseApi

interface GetOmpaListRepository {
    fun getOmpaList(page:Int, onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit)
}