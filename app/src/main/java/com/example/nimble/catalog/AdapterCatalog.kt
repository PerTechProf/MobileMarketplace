package com.example.nimble.catalog


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
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

            val logo = view.findViewById<ImageView>(R.id.logo)
            val nameItem = itemView.findViewById<TextView>(R.id.nameItem)
            val priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
            val rating = itemView.findViewById<RatingBar>(R.id.rating)

            Glide.with(view.context).load(good.logo).centerCrop().into(logo)
            nameItem.text = good.name
            priceStaff.text = good.price.toString()
            rating.rating = good.grade.toFloat()

            itemView.setOnClickListener{
                MAIN.navController.navigate(
                    R.id.action_miSearch_to_productFragment,
                    bundleOf(
                        "id" to good.id,
                        "logo" to good.logo,
                        "name" to good.name,
                        "price" to good.price,
                        "rating" to good.grade,
                        "description" to good.description,
                        "specification" to good.specification,
                        "vendorCode" to good.vendorCode
                    )
                )
                Log.d("MAIN", "Response: ${good.id}")
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




