package com.example.myapplication.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.kotlinacademy.db.database
import com.example.football2.db.Favorite
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterFavorite
import com.example.myapplication.helper.Config
import com.example.myapplication.ui.detail.DetailAllActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.intentFor

class FavoriteActivity : AppCompatActivity() {
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: AdapterFavorite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setTitle("Football Favorite")
        //adapter
        adapter = AdapterFavorite(this, favorites){
            startActivity(
                intentFor<DetailAllActivity>(
                    Config.KEY_FOOTBALL to it.teamId,
                    Config.KEY_ID_HOME to it.idHome,
                    Config.KEY_ID_WAY_TEAM to it.idAway
                )
            )
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        swLayout.setOnRefreshListener {
            showFavorite()
            swLayout.isRefreshing = false
        }
    }

    //show db sqlite
    private fun showFavorite(){
        favorites.clear()
        database.use {
            swLayout.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            //yang selanjutnya dimasukkan ke dalam data class Favorite untuk ditampilkan pada RecyclerView oleh adapter
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged() //refresh db
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}
