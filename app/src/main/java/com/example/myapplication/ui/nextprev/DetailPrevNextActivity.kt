package com.example.myapplication.ui.nextprev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.helper.Config
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.ResponseDetailPetandingan
import com.example.myapplication.model.team.ResponseTeam
import com.example.myapplication.model.team.TeamsItem
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import com.example.myapplication.services.DataRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_prev_next.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPrevNextActivity : AppCompatActivity() {
    var varOneTeam: TeamsItem? = null
    var varTwoTeam: TeamsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_prev_next)

        setTitle("Detail Pertandingan")
        var football: EventsItem = intent.getParcelableExtra(Config.KEY_FOOTBALL)

        loading.visibility = View.VISIBLE
        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        getDetailLiga(apiInterface, football.idEvent.toString())

        getDetailTeam1(football.idHomeTeam.toString())
        getDetailTeam2(football.idAwayTeam.toString())
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

                name.setText(football.strEvent)
                date.setText("Date : " + football.dateEvent)
                time.setText("Time Local : " + football.strTimeLocal)
                sport.setText("Sport : " + football.strSport)
                home.setText("Home Team : " + football.strHomeTeam)

                //varOneTeam = getDetailTeam(football.idHomeTeam.toString(), varOneTeam)
                //varTwoTeam = getDetailTeam(football.idAwayTeam.toString(), varTwoTeam)

                //oneTeam.setText(varOneTeam?.strTeam.toString())
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
                oneTeam.setText("Team: "+team.strTeam.toString())
                oneLeague.setText("League: "+team.strLeague.toString())
                oneCountry.setText("Country: "+team.strCountry.toString())
                oneStadium.setText("Stadium: "+team.strStadium.toString())
                Picasso.get().load(team.strTeamBadge.toString()).into(imgOne)
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
                twoTeam.setText("Team: "+team.strTeam.toString())
                twoLeague.setText("League: "+team.strLeague.toString())
                twoCountry.setText("Country: "+team.strCountry.toString())
                twoStadium.setText("Stadium: "+team.strStadium.toString())
                Picasso.get().load(team.strTeamBadge.toString()).into(imgTwo)
            }
        })
    }

    fun getDetailTeam(id: String): TeamsItem? {
        var team: TeamsItem? = null

        val service = DataRepository.create()
//        val service: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        service.getDetailTeam(id).enqueue(object : Callback<ResponseTeam> {
            override fun onFailure(call: Call<ResponseTeam>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResponseTeam>, response: Response<ResponseTeam>) {
                team = values(response.body()?.teams?.get(0) as TeamsItem)
                Log.e("response", team.toString())
            }
        })

        return team
    }

    fun values(datas: TeamsItem): TeamsItem = datas
}
