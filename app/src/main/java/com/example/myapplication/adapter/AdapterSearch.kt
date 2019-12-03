package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.search.EventItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cari_list.*

class AdapterSearch(
    private val context: Context,
    private val items: List<EventItem>
) : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.cari_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: EventItem) {
         /*   val skoring1 = if (items.intHomeScore == null)  "-" else items.intHomeScore.get(0)
            val skoring2 = if (items.intHomeScore == null)  "-" else items.intHomeScore.get(1)*/
            name.text = items.strEvent
            //skor.text = "$skoring1 : $skoring2"
            skor.text = if (items.intHomeScore == null)  "-" else items.intHomeScore
        }
    }
}