package com.example.myapplication.ui.detail

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.dicoding.kotlinacademy.db.database
import com.example.football2.db.Favorite
import com.example.myapplication.R
import com.example.myapplication.helper.Config
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.ResponseDetailPetandingan
import com.example.myapplication.model.team.ResponseTeam
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import com.example.myapplication.services.DataRepository
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_prev_next.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAllActivity : AppCompatActivity() {
    //menu action menu
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_prev_next)

        setTitle("Detail Pertandingan")

        loading.visibility = View.VISIBLE
        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        Favorite.teamId = intent.getStringExtra(Config.KEY_FOOTBALL) //get id
        Favorite.idHome = intent.getStringExtra(Config.KEY_ID_HOME) //get id
        Favorite.idAway = intent.getStringExtra(Config.KEY_ID_WAY_TEAM) //get id
        //cek state
        favoriteState()

        getDetailLiga(apiInterface, Favorite.teamId)
        getDetailTeam1(Favorite.idHome)
        getDetailTeam2(Favorite.idAway)
    }

    fun getDetailLiga(apiInterface: ApiInterface, idLiga: String) {
        val call: Call<ResponseDetailPetandingan> = apiInterface.getDetailPertandingan(idLiga)

        call.enqueue(object : Callback<ResponseDetailPetandingan> {
            override fun onFailure(call: Call<ResponseDetailPetandingan>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<ResponseDetailPetandingan>,
                response: Response<ResponseDetailPetandingan>
            ) {
                loading.visibility = View.GONE
                var football: EventsItem
                football = response.body()?.events?.get(0)!!

                //assigment value to favorite
                Favorite.teamName = football.strEvent.toString()
                Favorite.date = football.dateEvent.toString()
                Favorite.time = football.strTime.toString()
                Favorite.sport = football.strSport.toString()
                Favorite.homeTeam = football.strHomeTeam.toString()

                name.setText(Favorite.teamName)
                date.setText("Date : " + Favorite.date)
                time.setText("Time Local : " + Favorite.time)
                sport.setText("Sport : " + Favorite.sport)
                home.setText("Home Team : " + Favorite.homeTeam)

                //assigment value to favorite
                Favorite.skor = if(football.intHomeScore == null) "0" else football.intHomeScore+" : "+
                        if(football.intAwayScore == null) "0" else football.intAwayScore
                Favorite.goal = if (football.strHomeGoalDetails == null) "-" else football.strHomeGoalDetails.toString()
                Favorite.redCard = if (football.strHomeRedCards == null) "-" else football.strHomeRedCards.toString()
                Favorite.yellowCard = if (football.strHomeYellowCards == null) "-" else football.strHomeYellowCards.toString()

                skor.setText(Favorite.skor)
                goal.setText("Goal : " + Favorite.goal)
                redCard.setText("Red Card : " + Favorite.redCard)
                yellowCard.setText("Yellow Card : " + Favorite.yellowCard)
                oneLeague.setText(football.idHomeTeam.toString())
            }
        })
    }

    fun getDetailTeam1(id: String) {
        val service = DataRepository.create()
        service.getDetailTeam(id).enqueue(object : Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                val team: TeamsItem = response.body()?.teams?.get(0) as TeamsItem
                //assigment value to favorite
                Favorite.nameTeam1 = team.strTeam.toString()
                Favorite.legueTeam1 = team.strLeague.toString()
                Favorite.countryTeam1 = team.strCountry.toString()
                Favorite.stadiumTeam1 = team.strStadium.toString()
                Favorite.imgTeam1 = team.strTeamBadge.toString()

                oneTeam.setText("Team: "+Favorite.nameTeam1)
                oneLeague.setText("League: "+Favorite.legueTeam1)
                oneCountry.setText("Country: "+Favorite.countryTeam1)
                oneStadium.setText("Stadium: "+Favorite.stadiumTeam1)
                Picasso.get().load(Favorite.imgTeam1).into(imgOne)
            }
        })
    }

    fun getDetailTeam2(id: String) {
        val service = DataRepository.create()
        service.getDetailTeam(id).enqueue(object : Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                val team: TeamsItem = response.body()?.teams?.get(0) as TeamsItem
                //assigment value to favorite
                Favorite.nameTeam2 = team.strTeam.toString()
                Favorite.legueTeam2 = team.strLeague.toString()
                Favorite.countryTeam2 = team.strCountry.toString()
                Favorite.stadiumTeam2 = team.strStadium.toString()
                Favorite.imgTeam2 = team.strTeamBadge.toString()

                twoTeam.setText("Team: "+Favorite.nameTeam2)
                twoLeague.setText("League: "+Favorite.legueTeam2)
                twoCountry.setText("Country: "+Favorite.countryTeam2)
                twoStadium.setText("Stadium: "+Favorite.stadiumTeam2)
                Picasso.get().load(Favorite.imgTeam2).into(imgTwo)
            }
        })
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
                    Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_ID to Favorite.teamId,
                    Favorite.TEAM_NAME to Favorite.teamName,
                    Favorite.DATE to Favorite.date,
                    Favorite.TIME to Favorite.time,
                    Favorite.SKOR to Favorite.skor,
                    Favorite.SPORT to Favorite.sport,
                    Favorite.HOME_TEAM to Favorite.homeTeam,
                    Favorite.GOAL to Favorite.goal,
                    Favorite.RED_CARD to Favorite.redCard,
                    Favorite.YELLOW_CARD to Favorite.yellowCard,

                    //team 1
                    Favorite.ID_HOME to Favorite.idHome,
                    Favorite.IMG_TEAM_1 to Favorite.imgTeam1,
                    Favorite.LEGUE_TEAM_1 to Favorite.legueTeam1,
                    Favorite.COUNTRY_TEAM_1 to Favorite.countryTeam1,
                    Favorite.STADIUM_TEAM_1 to Favorite.stadiumTeam1,
                    Favorite.NAME_TEAM_1 to Favorite.nameTeam1,

                    //team 2
                    Favorite.ID_AWAY to Favorite.idAway,
                    Favorite.IMG_TEAM_2 to Favorite.imgTeam2,
                    Favorite.LEGUE_TEAM_2 to Favorite.legueTeam2,
                    Favorite.COUNTRY_TEAM_2 to Favorite.countryTeam2,
                    Favorite.STADIUM_TEAM_2 to Favorite.stadiumTeam2,
                    Favorite.NAME_TEAM_2 to Favorite.nameTeam2
                    )
            }
             Snackbar.make(name, "Added to favorite", Snackbar.LENGTH_LONG)
                             .setAction("Action", null).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(name, e.localizedMessage.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //delete db
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to Favorite.teamId)
            }
            Snackbar.make(name, "Removed to favorite", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } catch (e: SQLiteConstraintException){
            Snackbar.make(name, e.localizedMessage.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    //fungsi state
    //fungsi bantuan untuk mengecek status apakah sebuah tim sudah masuk ke dalam database atau belum.
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_ID = {id})",
                    "id" to Favorite.teamId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
