package com.example.myapplication.model.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSearch(

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
): Parcelable