package com.example.myapplication.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.kotlinacademy.db.database
import com.example.football2.db.FavoriteTim
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterFavoriteTimTim
import com.example.myapplication.helper.Config
import com.example.myapplication.ui.detail.DetailTimActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.intentFor

class FavoriteTimActivity : AppCompatActivity() {
    private var favorites: MutableList<FavoriteTim> = mutableListOf()
    private lateinit var adapter: AdapterFavoriteTimTim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setTitle("Football Favorite")
        //adapter
        adapter = AdapterFavoriteTimTim(this, favorites){
            startActivity(
                intentFor<DetailTimActivity>(
                    Config.KEY_FOOTBALL to it.teamId
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
            val result = select(FavoriteTim.TABLE_FAVORITE_TIM)
            //yang selanjutnya dimasukkan ke dalam data class Favorite untuk ditampilkan pada RecyclerView oleh adapter
            val favorite = result.parseList(classParser<FavoriteTim>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged() //refresh db
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}
