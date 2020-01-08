package com.example.myapplication.view

import com.example.myapplication.model.LeaguesItem

interface HomeView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<LeaguesItem>)
}