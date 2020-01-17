package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.football2.db.FavoriteTim
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.football_list.*

class AdapterFavoriteTimTim(
    private val context: Context,
    private val items: List<FavoriteTim>,
    private val listener: (FavoriteTim) -> Unit
) : RecyclerView.Adapter<AdapterFavoriteTimTim.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.football_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: FavoriteTim, listener: (FavoriteTim) -> Unit) {
            name.text = items.nameTeam
            items.imgTeam?.let { Picasso.get().load(it).into(image) }

            itemView.setOnClickListener { listener(items) }
        }
    }
}