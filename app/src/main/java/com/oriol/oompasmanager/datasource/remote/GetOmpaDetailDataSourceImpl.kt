package com.oriol.oompasmanager.datasource.remote

import com.oriol.oompasmanager.data.datasource.GetOmpaDetailDataSource
import com.oriol.oompasmanager.datasource.model.OmpaDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOmpaDetailDataSourceImpl constructor(
    private val api: DetailApi
) : GetOmpaDetailDataSource {

    override fun getOmpaDetail(OompaId:Int, onResult: (isSuccess: Boolean, ompaDetail: OmpaDetail?) -> Unit) {

        api.getOmpaDetail(OompaId).enqueue(object : Callback<OmpaDetail> {
                override fun onResponse(call: Call<OmpaDetail>?, response: Response<OmpaDetail>?) {
                    if (response != null && response.isSuccessful)
                        onResult(true, response.body()!!)
                    else
                        onResult(false, null)
                }

                override fun onFailure(call: Call<OmpaDetail>?, t: Throwable?) {
                    onResult(false, null)
                }
        })
    }
}
