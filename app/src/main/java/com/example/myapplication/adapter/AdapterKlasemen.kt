package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.klasemen.TableItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.klasemen_list.*

class AdapterKlasemen(
    private val context: Context,
    private val items: List<TableItem>,
    private val listener: (TableItem) -> Unit
) : RecyclerView.Adapter<AdapterKlasemen.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.klasemen_list, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    //implement layout container untuk binding
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(items: TableItem, listener: (TableItem) -> Unit) {
            name.text = items.name
            win.text = "win : ${items.win}"
            draw.text = "draw : ${items.draw}"
            los.text = "los : ${items.loss}"

            itemView.setOnClickListener { listener(items) }
        }
    }
}