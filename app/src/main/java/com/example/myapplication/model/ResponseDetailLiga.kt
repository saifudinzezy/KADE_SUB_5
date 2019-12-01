package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseDetailLiga(

	@field:SerializedName("leagues")
	val leagues: List<LeaguesItem?>? = null
) : Parcelable