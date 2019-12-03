package com.example.myapplication.model.search

import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
)