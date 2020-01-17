package com.example.myapplication.ui.tim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football2.api.ApiRepository
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterTim
import com.example.myapplication.helper.Config
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.presenter.NextPrevPresenter
import com.example.myapplication.presenter.TimPresenter
import com.example.myapplication.ui.detail.DetailTimActivity
import com.example.myapplication.view.TimView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_next.*
import org.jetbrains.anko.intentFor

class TimActivity  : AppCompatActivity(), TimView{
    //presenter
    private lateinit var prevPresenter: TimPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setTitle("Tim")

        var value: String = intent.getStringExtra(Config.KEY_FOOTBALL)
        rv.layoutManager = LinearLayoutManager(this)
        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = TimPresenter(this, request, gson)
        prevPresenter.getTim(value)
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun showTim(data: List<TeamsItem>) {
        rv.adapter = AdapterTim(this, data) {
            startActivity(
                intentFor<DetailTimActivity>(
                    Config.KEY_FOOTBALL to it.idTeam
                )
            )
        }
    }
}