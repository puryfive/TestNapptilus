package com.oriol.oompasmanager.datasource.model

import com.google.gson.annotations.SerializedName

data class OmpaDetail(

    @field:SerializedName("profession")
	val profession: String? = null,

    @field:SerializedName("image")
	val image: String? = null,

    @field:SerializedName("country")
	val country: String? = null,

    @field:SerializedName("gender")
	val gender: String? = null,

    @field:SerializedName("quota")
	val quota: String? = null,

    @field:SerializedName("last_name")
	val lastName: String? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("first_name")
	val firstName: String? = null,

    @field:SerializedName("favorite")
	val favorite: Favorite? = null,

    @field:SerializedName("age")
	val age: Int? = null,

    @field:SerializedName("email")
	val email: String? = null,

    @field:SerializedName("height")
	val height: Int? = null
)