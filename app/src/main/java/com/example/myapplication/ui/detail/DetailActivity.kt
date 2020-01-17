package com.example.myapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.football2.api.ApiRepository
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.helper.Config
import com.example.myapplication.model.ItemFootball
import com.example.myapplication.model.LeaguesItem
import com.example.myapplication.presenter.DetailLigaPresenter
import com.example.myapplication.ui.klasemen.KlasemenActivity
import com.example.myapplication.ui.nextprev.NextPrevActivity
import com.example.myapplication.ui.nextprev.PrevActivity
import com.example.myapplication.ui.search.SearchActivity
import com.example.myapplication.ui.tim.TimActivity
import com.example.myapplication.view.HomeView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import org.jetbrains.anko.intentFor

class DetailActivity : AppCompatActivity(), HomeView{
    //presenter
    private lateinit var presenter: DetailLigaPresenter
    private lateinit var teams: LeaguesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            startActivity(intentFor<SearchActivity>())
        }
        fab.invisible()

        var football: ItemFootball = intent.getParcelableExtra(Config.KEY_FOOTBALL)
        supportActionBar?.setTitle(football.name)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailLigaPresenter(this, request, gson)
        presenter.getTeamDetail(football.id)

        next.setOnClickListener {
            startActivity(intentFor<NextPrevActivity>(Config.KEY_FOOTBALL to football.id))
        }

        klasemen.setOnClickListener {
            startActivity(intentFor<KlasemenActivity>(Config.KEY_FOOTBALL to football.id))
        }

        tim.setOnClickListener {
            startActivity(intentFor<TimActivity>(Config.KEY_FOOTBALL to football.id))
        }

        prev.setOnClickListener {
            startActivity(intentFor<PrevActivity>(Config.KEY_FOOTBALL to football.id))
        }
    }

    override fun showLoading() {
        pb.visible()
        views.invisible()
    }

    override fun hideLoading() {
        pb.invisible()
        views.visible()
    }

    override fun showTeamList(data: List<LeaguesItem>) {
        teams = data.get(0)

        Picasso.get().load(teams.strLogo).into(iv_img)
        supportActionBar?.setTitle(teams.strLeague)
        tv_fottball_name.setText(teams.strLeague)
        tv_fottball_desc.setText(teams.strDescriptionEN)
    }

}
