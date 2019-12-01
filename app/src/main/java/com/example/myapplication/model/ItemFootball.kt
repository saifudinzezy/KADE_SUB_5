package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemFootball(val name: String, val id: String, val image: Int): Parcelable