package com.example.myapplication.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.extensions.TestContextProvider
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.view.NextPrevView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextPrevPresenterTest {
    private lateinit var presenter: NextPrevPresenter

    @Mock
    private lateinit var view: NextPrevView

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
        presenter = NextPrevPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getNextMatch() {
        val teams: MutableList<EventsItem> = mutableListOf()
        val response = ResponseNextPrevious(teams)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseNextPrevious::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getPreviousMatch() {
        val teams: MutableList<EventsItem> = mutableListOf()
        val response = ResponseNextPrevious(teams)
        val league = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ResponseNextPrevious::class.java
                )
            ).thenReturn(response)

            presenter.getPreviousMatch(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}