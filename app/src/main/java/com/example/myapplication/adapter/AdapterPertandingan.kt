package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.EventItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.cari_list.*

class AdapterPertandingan(
    private val context: Context,
    private val items: List<EventItem>,
    private val listener: (EventItem) -> Unit
) : RecyclerView.Adapter<AdapterPertandingan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.cari_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: EventItem, listener: (EventItem) -> Unit) {
            name.text = items.strEvent
            skor.text = items.intHomeScore

            itemView.setOnClickListener { listener(items) }
        }
    }
}