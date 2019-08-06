package com.oriol.oompasmanager.model.api

import com.oriol.oompasmanager.model.entities.OmpaDetail
import com.oriol.oompasmanager.model.entities.ResponseApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListApi {

    @GET("napptilus/oompa-loompas")
    fun getOmpas(
        @Query("page") page: Int = 1
    ): Call<ResponseApi>

    @GET("napptilus/oompa-loompas/{OompaId}")
    fun getOmpaDetail(
        @Path("OompaId") OompaId: Int
    ): Call<OmpaDetail>
}