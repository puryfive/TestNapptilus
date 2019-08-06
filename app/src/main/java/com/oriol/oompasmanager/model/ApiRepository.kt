package com.oriol.oompasmanager.model

import com.oriol.oompasmanager.model.api.ListApi
import com.oriol.oompasmanager.model.entities.OmpaDetail
import com.oriol.oompasmanager.model.entities.ResponseApi
import com.oriol.oompasmanager.utils.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {

    val listApi: ListApi

    init {
        listApi = createRetrofitClient()
    }

    private fun createRetrofitClient(): ListApi {

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(ListApi::class.java)
    }

    // GET Ompas list
    fun getOmpasList(onResult: (isSuccess: Boolean, ompasList: ResponseApi?) -> Unit) {

        listApi.getOmpas().enqueue(object : Callback<ResponseApi> {
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

    // GET Ompas detail
    fun getOmpaDetail(OompaId:Int, onResult: (isSuccess: Boolean, ompaDetail: OmpaDetail?) -> Unit) {

        listApi.getOmpaDetail(OompaId).enqueue(object : Callback<OmpaDetail> {
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

    companion object {
        private var INSTANCE: ApiRepository? = null
        fun getInstance() = INSTANCE
            ?: ApiRepository().also {
                INSTANCE = it
            }
    }
}