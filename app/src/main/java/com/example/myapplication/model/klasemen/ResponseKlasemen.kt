package com.example.myapplication.model.klasemen

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseKlasemen(

	@field:SerializedName("table")
	val table: List<TableItem?>? = null
): Parcelable