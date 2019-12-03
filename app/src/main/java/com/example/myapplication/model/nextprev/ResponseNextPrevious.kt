package com.example.myapplication.model.nextprev

import com.example.myapplication.model.EventsItem
import com.google.gson.annotations.SerializedName

data class ResponseNextPrevious(

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null
)