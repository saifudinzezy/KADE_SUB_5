package com.example.myapplication.view

import com.example.myapplication.model.team.TeamsItem

interface TimView {
    fun showLoading()
    fun hideLoading()
    fun showTim(data: List<TeamsItem>)
}