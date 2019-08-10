package com.oriol.oompasmanager.datasource.remote

import com.oriol.oompasmanager.data.datasource.GetOmpaListDataSource
import com.oriol.oompasmanager.datasource.model.ResponseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOmpaListDataSourceImpl constructor(
    private val api: ListApi
) : GetOmpaListDataSource {

    override fun getOmpaList(onResult: (isSuccess: Boolean, responseApi: ResponseApi?) -> Unit) {
        api.getOmpas().enqueue(object : Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>?, response: Response<ResponseApi>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<ResponseApi>?, t: Throwable?) {
                onResult(false, null)
            }
        })
    }
}
