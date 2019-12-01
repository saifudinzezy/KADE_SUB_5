package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponseNextPrevious(

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null
)