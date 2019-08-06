package com.oriol.oompasmanager.view.ui

import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.oriol.oompasmanager.R
import com.oriol.oompasmanager.model.ApiRepository
import com.oriol.oompasmanager.model.entities.ResultsItem

class MainActivity : AppCompatActivity() {

    val oompasListLive = MutableLiveData<List<ResultsItem?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiRepository.getInstance().getOmpasList { isSuccess, response ->
            if (isSuccess) {
                oompasListLive.value = response?.results
                println("Oompas List")
                oompasListLive.value?.forEach {
                    println(it)
                }
            } else {
                println("Error getting data")
            }
        }

        ApiRepository.getInstance().getOmpaDetail(1) { isSuccess, response ->
            if (isSuccess) {
                println("Oompas Detail Id=1")
                println(response)
            } else {
                println("Error getting data")
            }
        }
    }
}
