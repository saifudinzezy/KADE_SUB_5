package com.example.myapplication.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.AdapterPertandingan
import com.example.myapplication.model.EventItem
import com.example.myapplication.model.ResponsePencarianPertandingan
import com.example.myapplication.services.ApiClient
import com.example.myapplication.services.ApiInterface
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<EventItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setTitle("Pencarian pertandingan")

        rv.layoutManager = LinearLayoutManager(this)

        val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        btnCari.setOnClickListener { view ->
            getQuery(apiInterface, edQuery.text.toString())
        }
    }

    fun getQuery(apiInterface: ApiInterface, query: String) {
        loading.visibility = View.VISIBLE
        val call: Call<ResponsePencarianPertandingan> = apiInterface.getPencarianPertandingan(query)

        call.enqueue(object : Callback<ResponsePencarianPertandingan> {
            override fun onFailure(call: Call<ResponsePencarianPertandingan>, t: Throwable) {
                Toast.makeText(this@SearchActivity, "error " + t.message, Toast.LENGTH_SHORT)
            }

            override fun onResponse(
                call: Call<ResponsePencarianPertandingan>,
                response: Response<ResponsePencarianPertandingan>
            ) {
                loading.visibility = View.GONE
                try {
                    items = response!!.body()!!.event as ArrayList<EventItem>
                    rv.adapter = AdapterPertandingan(this@SearchActivity, items) {
//                        Toast.makeText(this@SearchActivity, "error " + items, Toast.LENGTH_SHORT)
                    }
                } catch (err: Exception) {
                    Log.e("Error", err.printStackTrace().toString())
                    Toast.makeText(this@SearchActivity, "error " + err.message, Toast.LENGTH_SHORT)
                }

            }

        })
    }

}
