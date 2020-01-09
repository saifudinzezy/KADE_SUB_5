package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.extensions.TestContextProvider
import com.example.myapplication.model.LeaguesItem
import com.example.myapplication.model.ResponseDetailLiga
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.view.HomeView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLigaPresenterTest {
    private lateinit var presenter: DetailLigaPresenter

    @Mock
    private lateinit var view: HomeView

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
        presenter = DetailLigaPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamDetail() {
        val teams: MutableList<LeaguesItem> = mutableListOf()
        val response = ResponseDetailLiga(teams)
        val league = "4328" //English Premier League

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseDetailLiga::class.java
                )
            ).thenReturn(response)

            presenter.getTeamDetail(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}