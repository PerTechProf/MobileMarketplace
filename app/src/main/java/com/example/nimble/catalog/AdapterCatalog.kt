package com.example.nimble.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nimble.R
import com.example.nimble.api.CatalogGoods
import retrofit2.Call


class AdapterCatalog(): RecyclerView.Adapter<AdapterCatalog.MyViewHolder>() {

    private var goods = mutableListOf<CatalogGoods>()

    fun setData(list: List<CatalogGoods>) {
        this.goods.addAll(list)
        notifyDataSetChanged()
    }


class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(catalogGoods: CatalogGoods) {
        val nameItem = itemView.findViewById<TextView>(R.id.nameItem)
        val priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
//            val logo = view.findViewById<ImageView>(R.id.logo)
        val description = itemView.findViewById<TextView>(R.id.description_staff)

        nameItem.text = catalogGoods.name
        description.text = catalogGoods.description
        priceStaff.text = catalogGoods.price.toString()

//            Glide.with(view.context).load(catalogGoods.logo).centerCrop().into(logo)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.staff_item, parent, false)
        return MyViewHolder(item)
    }

    override fun getItemCount(): Int {
        val data = goods
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(goods[position])
    }

//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        var nameItem = itemView.findViewById<TextView>(R.id.nameItem)
//        var priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
//        var description = itemView.findViewById<TextView>(R.id.description_staff)
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val item = LayoutInflater.from(parent.context).inflate(R.layout.staff_item, parent, false)
//        return ViewHolder(item)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.nameItem.text = goods[position].name
//        holder.priceStaff.text = goods[position].price.toString()
//        holder.description.text = goods[position].description
//    }
//
//    override fun getItemCount(): Int {
//        return goods.size
//    }



}




