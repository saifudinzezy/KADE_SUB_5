package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeaguesItem(

	@field:SerializedName("strDescriptionES")
	val strDescriptionES: String? = null,

	@field:SerializedName("dateFirstEvent")
	val dateFirstEvent: String? = null,

	@field:SerializedName("intFormedYear")
	val intFormedYear: String? = null,

	@field:SerializedName("strBanner")
	val strBanner: String? = null,

	@field:SerializedName("strSport")
	val strSport: String? = null,

	@field:SerializedName("strDescriptionIT")
	val strDescriptionIT: String? = null,

	@field:SerializedName("strDescriptionCN")
	val strDescriptionCN: String? = null,

	@field:SerializedName("strDescriptionEN")
	val strDescriptionEN: String? = null,

	@field:SerializedName("strWebsite")
	val strWebsite: String? = null,

	@field:SerializedName("strYoutube")
	val strYoutube: String? = null,

	@field:SerializedName("strDescriptionIL")
	val strDescriptionIL: String? = null,

	@field:SerializedName("idCup")
	val idCup: String? = null,

	@field:SerializedName("strComplete")
	val strComplete: String? = null,

	@field:SerializedName("strLocked")
	val strLocked: String? = null,

	@field:SerializedName("idLeague")
	val idLeague: String? = null,

	@field:SerializedName("idSoccerXML")
	val idSoccerXML: String? = null,

	@field:SerializedName("strTrophy")
	val strTrophy: String? = null,

	@field:SerializedName("strBadge")
	val strBadge: String? = null,

	@field:SerializedName("strTwitter")
	val strTwitter: String? = null,

	@field:SerializedName("strDescriptionHU")
	val strDescriptionHU: String? = null,

	@field:SerializedName("strGender")
	val strGender: String? = null,

	@field:SerializedName("strLeagueAlternate")
	val strLeagueAlternate: String? = null,

	@field:SerializedName("strDescriptionSE")
	val strDescriptionSE: String? = null,

	@field:SerializedName("strNaming")
	val strNaming: String? = null,

	@field:SerializedName("strDivision")
	val strDivision: String? = null,

	@field:SerializedName("strDescriptionJP")
	val strDescriptionJP: String? = null,

	@field:SerializedName("strFanart1")
	val strFanart1: String? = null,

	@field:SerializedName("strDescriptionFR")
	val strDescriptionFR: String? = null,

	@field:SerializedName("strFanart2")
	val strFanart2: String? = null,

	@field:SerializedName("strFanart3")
	val strFanart3: String? = null,

	@field:SerializedName("strFacebook")
	val strFacebook: String? = null,

	@field:SerializedName("strFanart4")
	val strFanart4: String? = null,

	@field:SerializedName("strCountry")
	val strCountry: String? = null,

	@field:SerializedName("strDescriptionNL")
	val strDescriptionNL: String? = null,

	@field:SerializedName("strRSS")
	val strRSS: String? = null,

	@field:SerializedName("strDescriptionRU")
	val strDescriptionRU: String? = null,

	@field:SerializedName("strDescriptionPT")
	val strDescriptionPT: String? = null,

	@field:SerializedName("strLogo")
	val strLogo: String? = null,

	@field:SerializedName("strDescriptionDE")
	val strDescriptionDE: String? = null,

	@field:SerializedName("strDescriptionNO")
	val strDescriptionNO: String? = null,

	@field:SerializedName("strLeague")
	val strLeague: String? = null,

	@field:SerializedName("strPoster")
	val strPoster: String? = null,

	@field:SerializedName("strDescriptionPL")
	val strDescriptionPL: String? = null
): Parcelable