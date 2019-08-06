package com.oriol.oompasmanager.model.entities

import com.google.gson.annotations.SerializedName

data class ResponseApi(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)