package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.extensions.TestContextProvider
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.ResponseDetailPetandingan
import com.example.myapplication.model.team.ResponseTeam
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.view.DetailAllView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailAllPresenterTest {
    private lateinit var presenter: DetailAllPresenter

    @Mock
    private lateinit var view: DetailAllView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    //Untuk bisa menginisialisasi semua mock object tersebut sebelum fungsi pengujian dijalankan
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //menginisialisasikan presenter
        presenter = DetailAllPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailTeams() {
        val teams: MutableList<EventsItem> = mutableListOf()
        val response = ResponseDetailPetandingan(teams)
        val league = "670825" //SV Salzburg vs Liverpool

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseDetailPetandingan::class.java
                )
            ).thenReturn(response)

            presenter.getDetailTeams(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailLiga(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getDetailHome() {
        val teams: MutableList<TeamsItem> = mutableListOf()
        val response = ResponseTeam(teams)
        val league = "133970" //SV Salzburg

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseTeam::class.java
                )
            ).thenReturn(response)

            presenter.getDetailHome(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailHome(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getDetailAway() {
        val teams: MutableList<TeamsItem> = mutableListOf()
        val response = ResponseTeam(teams)
        val league = "133602" //Liverpool

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseTeam::class.java
                )
            ).thenReturn(response)

            presenter.getDetailAway(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailaway(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}