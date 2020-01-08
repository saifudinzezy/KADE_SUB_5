package com.example.football2.api

import android.net.Uri
import com.example.myapplication.BuildConfig

object TheSportDBApi {
    //sebuah fungsi untuk menampung endpoint yang akan digunakan
    fun getDetailLiga(idLeague: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" + idLeague
    }

    //get detail football
    fun getTeamDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }
}