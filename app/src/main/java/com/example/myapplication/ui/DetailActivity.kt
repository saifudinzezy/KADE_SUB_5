package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.helper.Config
import com.example.myapplication.model.ItemFootball
import com.example.myapplication.model.LeaguesItem
import com.example.myapplication.model.ResponseDetailLiga
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import com.example.myapplication.ui.nextprev.NextActivity
import com.example.myapplication.ui.nextprev.PrevActivity
import com.example.myapplication.ui.search.SearchActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            startActivity(intentFor<SearchActivity>())
        }

        var football: ItemFootball = intent.getParcelableExtra(Config.KEY_FOOTBALL)
        supportActionBar?.setTitle(football.name)

        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        getDetailLiga(apiInterface, football.id)

        next.setOnClickListener {
            startActivity(intentFor<NextActivity>(Config.KEY_FOOTBALL to football.id))
        }

        prev.setOnClickListener {
            startActivity(intentFor<PrevActivity>(Config.KEY_FOOTBALL to football.id))
        }
    }

    fun getDetailLiga(apiInterface: ApiInterface, idLiga: String) {
        val call: Call<ResponseDetailLiga> = apiInterface.getDetailLiga(idLiga)

        call.enqueue(object : Callback<ResponseDetailLiga> {
            override fun onFailure(call: Call<ResponseDetailLiga>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<ResponseDetailLiga>,
                response: Response<ResponseDetailLiga>
            ) {
                views.visibility = View.VISIBLE
                pb.visibility = View.GONE

                var football: LeaguesItem
                football = response.body()?.leagues?.get(0)!!

                Picasso.get().load(football.strLogo).into(iv_img)
                supportActionBar?.setTitle(football.strLeague)
                tv_fottball_name.setText(football.strLeague)
                tv_fottball_desc.setText(football.strDescriptionEN)
            }

        })
    }

}
