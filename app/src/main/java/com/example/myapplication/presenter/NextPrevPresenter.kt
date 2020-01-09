package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.view.NextPrevView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NextPrevPresenter(private val prevView: NextPrevView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getNextMatch(teamId: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getNextMatch(teamId)).await(),
                ResponseNextPrevious::class.java)

           prevView.showTeamList(data.events?.filter { it?.strSport == "Soccer" } as List<EventsItem>)
            prevView.hideLoading()
        }
    }

    fun getPreviousMatch(teamId: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getPreviousMatch(teamId)).await(),
                ResponseNextPrevious::class.java)

            prevView.showTeamList(data.events?.filter { it?.strSport == "Soccer" } as List<EventsItem>)
            prevView.hideLoading()
        }
    }
}