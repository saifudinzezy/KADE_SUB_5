package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDetailPetandingan(

	@field:SerializedName("events")
	val events: List<EventsItem>
): Parcelable