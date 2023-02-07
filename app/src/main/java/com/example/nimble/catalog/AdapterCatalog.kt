package com.example.nimble.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nimble.R
import com.example.nimble.api.CatalogGoods
import retrofit2.Call
import retrofit2.Response


class AdapterCatalog(): RecyclerView.Adapter<AdapterCatalog.ViewHolder>() {

    var listItem = mutableListOf<CatalogGoods>()
    var myList = emptyList<CatalogGoods>()

    fun setData(list: List<CatalogGoods>) {
        this.listItem.clear()
        this.listItem.addAll(list)
        notifyDataSetChanged()
    }

    fun addData(element: CatalogGoods) {
        this.listItem.add(element)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameItem = itemView.findViewById<TextView>(R.id.nameItem)
        var priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
        var description = itemView.findViewById<TextView>(R.id.description_staff)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.staff_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameItem.text = listItem[position].name
        holder.priceStaff.text = listItem[position].price.toString()
        holder.description.text = listItem[position].description
    }

    override fun getItemCount(): Int {
        return listItem.size
    }



}




