package com.example.myapplication.api

import com.example.football2.api.ApiRepository
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

//TODO CTRL + SHIFT + T
class ApiRepositoryTest {

    @Test
    fun doRequestAsync() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequestAsync(url)
        verify(apiRepository).doRequestAsync(url)
    }
}