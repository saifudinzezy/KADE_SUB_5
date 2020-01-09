package com.example.myapplication.view

import com.example.myapplication.model.EventsItem

interface NextPrevView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventsItem>)
}