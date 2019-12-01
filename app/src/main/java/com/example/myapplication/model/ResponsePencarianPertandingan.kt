package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponsePencarianPertandingan(

	@field:SerializedName("event")
	val event: List<EventItem>
): Parcelable