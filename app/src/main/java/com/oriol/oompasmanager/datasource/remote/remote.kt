package com.oriol.oompasmanager.datasource.remote

import com.oriol.oompasmanager.datasource.model.OmpaDetail
import com.oriol.oompasmanager.datasource.model.ResponseApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListApi {

    @GET("napptilus/oompa-loompas")
    fun getOmpas(
        @Query("page") page: Int = 1
    ): Call<ResponseApi>
}

interface DetailApi{

    @GET("napptilus/oompa-loompas/{OompaId}")
    fun getOmpaDetail(
        @Path("OompaId") OompaId: Int
    ): Call<OmpaDetail>
}