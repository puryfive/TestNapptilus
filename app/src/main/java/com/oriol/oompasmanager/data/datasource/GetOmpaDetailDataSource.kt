package com.oriol.oompasmanager.data.datasource

import com.oriol.oompasmanager.datasource.model.OmpaDetail

interface GetOmpaDetailDataSource {
    fun getOmpaDetail(OompaId:Int, onResult: (isSuccess: Boolean, ompaDetail: OmpaDetail?) -> Unit)
}