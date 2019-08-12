package com.oriol.oompasmanager.datasource.model

import com.google.gson.annotations.SerializedName
import com.oriol.oompasmanager.domain.model.ResultsItem

data class ResponseApi(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)