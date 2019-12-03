package com.example.myapplication.services

import com.example.myapplication.model.ResponseDetailLiga
import com.example.myapplication.model.ResponseDetailPetandingan
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.model.search.ResponseSearch
import com.example.myapplication.model.team.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //@GET("myObjects/{idLeague}")
    //fun myObjectById(@Path("idLeague") id: Int?, @Query("a_param") aParam: String?): Call<ResponseDetailLiga>

    //Detail liga
    //https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id={idLeague}
    @GET("lookupleague.php")
    fun getDetailLiga(@Query("id") idLeague : String) : Call<ResponseDetailLiga>

    //Daftar next match
    //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id={idLeague}
    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") idLeague : String) : Call<ResponseNextPrevious>

    //Daftar previous match
    //https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id={idLeague}
    @GET("eventspastleague.php")
    fun getPreviousMatch(@Query("id") idLeague : String) : Call<ResponseNextPrevious>

    //Detail pertandingan
    // https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id={idEvent}
    @GET("lookupevent.php")
    fun getDetailPertandingan(@Query("id") idEvent : String) : Call<ResponseDetailPetandingan>

    //Pencarian pertandingan
    //https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e={query}
    @GET("searchevents.php")
    fun getSearch(@Query("e") query: String) : Call<ResponseSearch>

    //Detail Team
    // https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id={idEvent}
    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") idEvent : String) : Call<ResponseTeam>
}