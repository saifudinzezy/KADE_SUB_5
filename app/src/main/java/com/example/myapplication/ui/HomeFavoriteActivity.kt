package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.ui.favorite.FavoriteActivity
import com.example.myapplication.ui.favorite.FavoriteTimActivity
import kotlinx.android.synthetic.main.activity_home_favorite.*
import org.jetbrains.anko.intentFor

class HomeFavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_favorite)

        favTim.setOnClickListener {
            startActivity(intentFor<FavoriteTimActivity>())
        }

        favMatch.setOnClickListener {
            startActivity(intentFor<FavoriteActivity>())
        }
    }
}
