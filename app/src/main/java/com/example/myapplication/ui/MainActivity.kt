package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterFootball
import com.example.myapplication.helper.Config
import com.example.myapplication.model.ItemFootball
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {
    private var items: MutableList<ItemFootball> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("List Liga")

        val list = findViewById<RecyclerView>(R.id.club_list)
        initData()

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = AdapterFootball(this, items){
            startActivity(intentFor<ScrollingActivity>(Config.KEY_FOOTBALL to it))
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val id = resources.getStringArray(R.array.club_id)
        val image = resources.obtainTypedArray(R.array.club_image)

        items.clear()

        for (i in name.indices) {
            items.add(
                ItemFootball(
                    name[i],
                    id[i],
                    image.getResourceId(i, 0)
                )
            )
        }

        //Recycle the typed array
        image.recycle()
    }
}
