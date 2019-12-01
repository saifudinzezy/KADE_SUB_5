package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.EventsItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.nextprev_list.*
import kotlinx.android.synthetic.main.nextprev_list.view.*

class AdapterNextPrev(
    private val context: Context,
    private val items: List<EventsItem>,
    private val listener: (EventsItem) -> Unit
) : RecyclerView.Adapter<AdapterNextPrev.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.nextprev_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: EventsItem, listener: (EventsItem) -> Unit) {
            name.text = items.strEvent
            date.text = "Tanggal : ${items.dateEvent}"
            time.text = "Waktu : ${items.strTimeLocal}"

            itemView.setOnClickListener { listener(items) }
        }
    }
}