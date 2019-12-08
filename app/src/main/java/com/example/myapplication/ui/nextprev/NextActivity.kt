package com.example.myapplication.ui.nextprev

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterNextPrev
import com.example.myapplication.helper.Config
import com.example.myapplication.model.EventsItem
import com.example.myapplication.model.nextprev.ResponseNextPrevious
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import kotlinx.android.synthetic.main.activity_next.*
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<EventsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        setTitle("Jadwal Pertandingan")

        var value: String = intent.getStringExtra(Config.KEY_FOOTBALL)
        rv.layoutManager = LinearLayoutManager(this)
        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        getQuery(apiInterface, value)
    }

    fun getQuery(apiInterface: ApiInterface, query: String) {
        loading.visibility = View.VISIBLE
        val call: Call<ResponseNextPrevious> = apiInterface.getNextMatch(query)

        call.enqueue(object : Callback<ResponseNextPrevious> {
            override fun onFailure(call: Call<ResponseNextPrevious>, t: Throwable) {
                Toast.makeText(this@NextActivity, "error " + t.message, Toast.LENGTH_SHORT)
            }

            override fun onResponse(
                call: Call<ResponseNextPrevious>,
                response: Response<ResponseNextPrevious>
            ) {
                loading.visibility = View.GONE
                try {
                    items =
                        response!!.body()!!.events?.filter { it?.strSport == "Soccer" } as ArrayList<EventsItem>
                    rv.adapter = AdapterNextPrev(this@NextActivity, items) {
                        startActivity(intentFor<DetailPrevNextActivity>(Config.KEY_FOOTBALL to it))
                    }
                } catch (err: Exception) {
                    Log.e("Error", err.printStackTrace().toString())
                    Toast.makeText(this@NextActivity, "error " + err.message, Toast.LENGTH_SHORT)
                }

            }

        })
    }

}
