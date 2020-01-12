package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.extensions.CoroutineContextProvider
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.klasemen.ResponseKlasemen
import com.example.myapplication.model.klasemen.TableItem
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.view.KlasemenView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KlasemenPresenter(private val prevView: KlasemenView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getKlasemen(idLegue: String?){
        prevView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getKlasemen(idLegue)).await(),
                ResponseKlasemen::class.java)

           prevView.showTeamList(data.table as List<TableItem>)
            prevView.hideLoading()
        }
    }
}