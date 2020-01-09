package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.extensions.TestContextProvider
import com.example.myapplication.model.search.EventItem
import com.example.myapplication.model.search.ResponseSearch
import com.example.myapplication.view.SearchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {
    private lateinit var presenter: SearchPresenter

    @Mock
    private lateinit var view: SearchView

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
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getSearch() {
        val teams: MutableList<EventItem> = mutableListOf()
        val response = ResponseSearch(teams)
        val league = "liverpool" 

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseSearch::class.java
                )
            ).thenReturn(response)

            presenter.getSearch(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}