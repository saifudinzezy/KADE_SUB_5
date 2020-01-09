package com.example.myapplication.view

import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.team.TeamsItem

interface DetailAllView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLiga(data: List<EventsItem>)
    fun showDetailHome(data: List<TeamsItem>)
    fun showDetailaway(data: List<TeamsItem>)
}