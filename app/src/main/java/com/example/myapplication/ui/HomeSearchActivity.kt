package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.search.SearchActivity
import com.example.myapplication.ui.search.SearchTimActivity
import kotlinx.android.synthetic.main.activity_home_search.*
import org.jetbrains.anko.intentFor

class HomeSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_search)

        tim.setOnClickListener {
            startActivity(intentFor<SearchTimActivity>())
        }

        match.setOnClickListener {
            startActivity(intentFor<SearchActivity>())
        }
    }
}
