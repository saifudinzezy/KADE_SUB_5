package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.ResponseDetailPetandingan
import com.example.myapplication.model.team.ResponseTeam
import com.example.myapplication.view.DetailAllView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//TODO CTRL + SHIFT + T
class DetailAllPresenter(private val prevView: DetailAllView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailTeams(teamId: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailPertandingan(teamId)).await(),
                ResponseDetailPetandingan::class.java)

            prevView.showDetailLiga(data.events)
            prevView.hideLoading()
        }
    }

    fun getDetailHome(idEvent: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(idEvent)).await(),
                ResponseTeam::class.java)

            prevView.showDetailHome(data.teams)
            prevView.hideLoading()
        }
    }

    fun getDetailAway(idEvent: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetailTeam(idEvent)).await(),
                ResponseTeam::class.java)

            prevView.showDetailaway(data.teams)
            prevView.hideLoading()
        }
    }
}