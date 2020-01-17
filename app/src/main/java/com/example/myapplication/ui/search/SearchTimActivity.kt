package com.example.myapplication.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football2.api.ApiRepository
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterSearch
import com.example.myapplication.adapter.AdapterTim
import com.example.myapplication.helper.Config
import com.example.myapplication.helper.EspressoIdlingResource
import com.example.myapplication.model.search.EventItem
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.presenter.SearchPresenter
import com.example.myapplication.presenter.TimPresenter
import com.example.myapplication.ui.detail.DetailAllActivity
import com.example.myapplication.ui.detail.DetailTimActivity
import com.example.myapplication.view.SearchView
import com.example.myapplication.view.TimView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.intentFor

class SearchTimActivity : AppCompatActivity(), TimView{
    //presenter
    private lateinit var presenter: TimPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setTitle("Pencarian Tim")

        rv.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TimPresenter(this, request, gson)

        btnCari.setOnClickListener {
            //Memberitahukan Espresso bahwa aplikasi sedang sibuk
            EspressoIdlingResource.increment()
            presenter.getSearchTim(edQuery.text.toString())
        }
    }

    override fun showLoading() {
        loading.visible()
        //flag.setText("load")
    }

    override fun hideLoading() {
        loading.invisible()
        //flag.setText("loaded")
    }

    override fun showTim(data: List<TeamsItem>) {
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            //Memberitahukan bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }

        rv.adapter = AdapterTim(this, data) {
            startActivity(
                intentFor<DetailTimActivity>(
                    Config.KEY_FOOTBALL to it.idTeam
                )
            )
        }
    }
}
