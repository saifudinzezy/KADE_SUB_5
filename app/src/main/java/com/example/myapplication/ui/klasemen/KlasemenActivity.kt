package com.example.myapplication.ui.klasemen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football2.api.ApiRepository
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterKlasemen
import com.example.myapplication.adapter.AdapterNextPrev
import com.example.myapplication.helper.Config
import com.example.myapplication.model.klasemen.TableItem
import com.example.myapplication.presenter.KlasemenPresenter
import com.example.myapplication.presenter.NextPrevPresenter
import com.example.myapplication.ui.detail.DetailAllActivity
import com.example.myapplication.view.KlasemenView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_next.*
import org.jetbrains.anko.intentFor

class KlasemenActivity : AppCompatActivity(), KlasemenView{
    //presenter
    private lateinit var prevPresenter: KlasemenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        setTitle("Klasemen Pertandingan")

        var value: String = intent.getStringExtra(Config.KEY_FOOTBALL)
        rv.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        prevPresenter = KlasemenPresenter(this, request, gson)
        prevPresenter.getKlasemen(value)
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun showTeamList(data: List<TableItem>) {
        rv.adapter = AdapterKlasemen(this, data) {
            //code
        }
    }
}
