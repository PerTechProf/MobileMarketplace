package com.example.nimble.catalog


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.api.Good


class AdapterCatalog(): RecyclerView.Adapter<AdapterCatalog.MyViewHolder>() {

    private var goods = listOf<Good>()

    fun setData(list: List<Good>) {
        this.goods = list
        notifyDataSetChanged()
    }


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(good: Good) {
            val nameItem = itemView.findViewById<TextView>(R.id.nameItem)
            val priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
            val logo = view.findViewById<ImageView>(R.id.logo)
            val description = itemView.findViewById<TextView>(R.id.description_staff)

            nameItem.text = good.name
            description.text = good.description
            priceStaff.text = good.price.toString()
            Glide.with(view.context).load(good.logo).centerCrop().into(logo)

            itemView.setOnClickListener{
                MAIN.navController.navigate(
                    R.id.action_miSearch_to_productFragment,
                    bundleOf(
                        "name" to good.name,
                        "description" to good.description,
                        "price" to good.price,
                        "logo" to good.logo,
                        "specification" to good.specification

                    )
                )
            }
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





}




