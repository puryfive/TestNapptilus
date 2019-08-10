package com.oriol.oompasmanager.data.datasource

import com.oriol.oompasmanager.datasource.model.ResponseApi

interface GetOmpaListDataSource {
    fun getOmpaList(onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit)
}