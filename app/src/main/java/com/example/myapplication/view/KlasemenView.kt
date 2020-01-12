package com.example.myapplication.view

import com.example.myapplication.model.klasemen.TableItem

interface KlasemenView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TableItem>)
}