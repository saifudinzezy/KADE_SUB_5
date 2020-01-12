package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.favorite.FavoriteActivity
import com.example.myapplication.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.jetbrains.anko.intentFor

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        fav.setOnClickListener {
            startActivity(intentFor<FavoriteActivity>())

        }

        liga.setOnClickListener {
            startActivity(intentFor<HomeActivity>())

        }

        search.setOnClickListener {
            startActivity(intentFor<SearchActivity>())

        }
    }
}
