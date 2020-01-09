package com.example.myapplication.view

import com.example.myapplication.model.search.EventItem

interface SearchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventItem>)
}