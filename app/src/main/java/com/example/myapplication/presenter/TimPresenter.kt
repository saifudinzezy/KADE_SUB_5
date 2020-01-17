package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.team.ResponseTeam
import com.example.myapplication.view.TimView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//TODO CTRL + SHIFT + T
class TimPresenter(private val prevView: TimView,
                   private val apiRepository: ApiRepository,
                   private val gson: Gson,
                   private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTim(teamId: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getTim(teamId)).await(),
                ResponseTeam::class.java)

            prevView.showTim(data.teams)
            prevView.hideLoading()
        }
    }

    fun getSearchTim(query: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getSearchTim(query)).await(),
                ResponseTeam::class.java)

            prevView.showTim(data.teams)
            prevView.hideLoading()
        }
    }

    fun getDetailTim(teamId: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(teamId)).await(),
                ResponseTeam::class.java)

            prevView.showTim(data.teams)
            prevView.hideLoading()
        }
    }
}