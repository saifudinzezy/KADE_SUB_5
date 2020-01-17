package com.example.myapplication.ui.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dicoding.kotlinacademy.db.database
import com.example.football2.api.ApiRepository
import com.example.football2.db.FavoriteTim
import com.example.football2.extensions.invisible
import com.example.football2.extensions.visible
import com.example.myapplication.R
import com.example.myapplication.helper.Config
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.presenter.TimPresenter
import com.example.myapplication.view.TimView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tim.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTimActivity : AppCompatActivity(), TimView{
    private lateinit var presenter: TimPresenter
    //menu action menu
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tim)

        FavoriteTim.teamIdTim = intent.getStringExtra(Config.KEY_FOOTBALL)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = TimPresenter(this, request, gson)
        presenter.getDetailTim(FavoriteTim.teamIdTim )
    }

    override fun showLoading() {
        loading.visible()
    }

    override fun hideLoading() {
        loading.invisible()
    }

    override fun showTim(datas: List<TeamsItem>) {
        var football: TeamsItem = datas.get(0)
        //assigment value to favorite
        FavoriteTim.nameTeamTim = football.strTeam.toString()
        FavoriteTim.legueTeamTim = football.strLeague.toString()
        FavoriteTim.countryTeamTim = football.strCountry.toString()
        FavoriteTim.stadiumTeamTim = football.strStadium.toString()
        FavoriteTim.imgTeamTim = football.strTeamBadge.toString()

        oneTeam.setText("Team: "+ FavoriteTim.nameTeamTim)
        oneLeague.setText("League: "+ FavoriteTim.legueTeamTim)
        oneCountry.setText("Country: "+ FavoriteTim.countryTeamTim )
        oneStadium.setText("Stadium: "+ FavoriteTim.stadiumTeamTim)
        Picasso.get().load(FavoriteTim.imgTeamTim).into(imgOne)
    }


    //menus
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //set icon
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    //membuat fungsi baru untuk memasukkan data ke dalam database
    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    FavoriteTim.TABLE_FAVORITE_TIM,
                    FavoriteTim.TEAM_ID_TIM to FavoriteTim.teamIdTim,
                    FavoriteTim.NAME_TEAM_TIM to FavoriteTim.nameTeamTim,
                    FavoriteTim.LEGUE_TEAM_TIM to FavoriteTim.legueTeamTim,
                    FavoriteTim.COUNTRY_TEAM_TIM to FavoriteTim.countryTeamTim,
                    FavoriteTim.STADIUM_TEAM_TIM to FavoriteTim.stadiumTeamTim,
                    FavoriteTim.IMG_TEAM_TIM to FavoriteTim.imgTeamTim

                )
            }
            Snackbar.make(oneTeam, "Added to favorite", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(oneTeam, e.localizedMessage.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //delete db
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTim.TABLE_FAVORITE_TIM, "(TEAM_ID_TIM = {id})",
                    "id" to FavoriteTim.teamIdTim)
            }
            Snackbar.make(oneTeam, "Removed to favorite", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(oneTeam, e.localizedMessage.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //fungsi state
    //fungsi bantuan untuk mengecek status apakah sebuah tim sudah masuk ke dalam database atau belum.
    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTim.TABLE_FAVORITE_TIM)
                .whereArgs("(TEAM_ID_TIM = {id})",
                    "id" to FavoriteTim.teamIdTim)
            val favorite = result.parseList(classParser<FavoriteTim>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
