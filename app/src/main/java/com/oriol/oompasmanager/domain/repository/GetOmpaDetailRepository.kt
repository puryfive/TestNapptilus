package com.oriol.oompasmanager.domain.repository

import com.oriol.oompasmanager.datasource.model.OmpaDetail

interface GetOmpaDetailRepository {
     fun getOmpaDetail(ompaId: Int, onResult: (isSuccess: Boolean, ompasList: OmpaDetail?) -> Unit)
}