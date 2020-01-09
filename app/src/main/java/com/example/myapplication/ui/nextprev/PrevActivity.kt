package com.example.myapplication.ui.nextprev

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football2.api.ApiRepository
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterNextPrev
import com.example.myapplication.helper.Config
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.presenter.NextPrevPresenter
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import com.example.myapplication.ui.detail.DetailAllActivity
import com.example.myapplication.view.NextPrevView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_next.*
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevActivity : AppCompatActivity(), NextPrevView{
    //presenter
    private lateinit var prevPresenter: NextPrevPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setTitle("Jadwal Pertandingan")

        var value: String = intent.getStringExtra(Config.KEY_FOOTBALL)
        rv.layoutManager = LinearLayoutManager(this)
        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = NextPrevPresenter(this, request, gson)
        prevPresenter.getPreviousMatch(value)
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun showTeamList(data: List<EventsItem>) {
        rv.adapter = AdapterNextPrev(this@PrevActivity, data) {
            startActivity(
                intentFor<DetailAllActivity>(
                    Config.KEY_FOOTBALL to it.idEvent,
                    Config.KEY_ID_HOME to it.idHomeTeam,
                    Config.KEY_ID_WAY_TEAM to it.idAwayTeam
                )
            )
        }
    }
}
