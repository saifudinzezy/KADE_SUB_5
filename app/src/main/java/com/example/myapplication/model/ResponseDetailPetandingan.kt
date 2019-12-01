package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailPetandingan(

	@field:SerializedName("events")
	val events: List<EventsItem>
)