package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.LeaguesItem
import com.example.myapplication.model.ResponseDetailLiga
import com.example.myapplication.view.HomeView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailLigaPresenter(private val view: HomeView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(teamId: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailLiga(teamId)).await(),
                ResponseDetailLiga::class.java)

           view.showTeamList(data.leagues as List<LeaguesItem>)
            view.hideLoading()
        }
    }
}