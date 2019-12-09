package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.football2.db.Favorite
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.nextprev_list.*

class AdapterFavorite(
    private val context: Context,
    private val items: List<Favorite>,
    private val listener: (Favorite) -> Unit
) : RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.nextprev_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {
            name.text = items.teamName
            date.text = "Tanggal : ${items.date}"
            time.text = "Waktu : ${items.time}"

            itemView.setOnClickListener { listener(items) }
        }
    }
}