package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.team.TeamsItem
import com.squareup.picasso.Picasso

class AdapterTim(private val context: Context, private val items: List<TeamsItem>, private val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<AdapterTim.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.football_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: TeamsItem, listener: (TeamsItem) -> Unit) {
            name.text = items.strTeam
            items.strTeamBadge?.let { Picasso.get().load(it).into(image) }
            itemView.setOnClickListener { listener(items) }
        }
    }
}