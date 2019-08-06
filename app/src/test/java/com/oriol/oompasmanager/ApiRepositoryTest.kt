package com.oriol.oompasmanager

import com.oriol.oompasmanager.model.ApiRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ApiRepositoryTest {

    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        this.apiRepository = ApiRepository()
    }

    @Test
    fun get_ompas_list_gets_data() {
        val responseApi = apiRepository.listApi.getOmpas().execute()
        assertNotNull(responseApi)
        assertTrue(responseApi.isSuccessful)
    }

    @Test
    fun get_ompas_detail_gets_data_for_id_1() {
        val ompaDetail = apiRepository.listApi.getOmpaDetail(1).execute()
        assertNotNull(ompaDetail)
        assertTrue(ompaDetail.isSuccessful)
    }
}
