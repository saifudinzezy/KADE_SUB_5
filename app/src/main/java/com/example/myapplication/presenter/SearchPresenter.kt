package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.search.EventItem
import com.example.myapplication.model.search.ResponseSearch
import com.example.myapplication.view.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchPresenter(private val view: SearchView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getSearch(query: String?){
        view.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getSearch(query)).await(),
                ResponseSearch::class.java)

           view.showTeamList(data.event as List<EventItem>)
            view.hideLoading()
        }
    }
}