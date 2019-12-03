package com.example.myapplication.model.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseTeam(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
): Parcelable