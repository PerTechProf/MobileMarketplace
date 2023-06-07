package com.example.nimble.pannier


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
import com.example.nimble.api.Carts
import com.example.nimble.api.Good

class ShoppingCartAdapter : RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolderCart>(){

    private var goods = listOf<Carts>()

    fun setList(list: List<Carts>){
        this.goods = list
        notifyDataSetChanged()
    }



    class MyViewHolderCart(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cartItem: Carts) {

            val logo = view.findViewById<ImageView>(R.id.logo)
            val nameItem = itemView.findViewById<TextView>(R.id.nameItem)
            val priceStaff = itemView.findViewById<TextView>(R.id.price_staff)
            val quantity = itemView.findViewById<TextView>(R.id.quantity)

            Glide.with(view.context).load(cartItem.product.logo).centerCrop().into(logo)
            nameItem.text = cartItem.product.name
            priceStaff.text = cartItem.product.price.toString()
            quantity.text = cartItem.quantity.toString()



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCart{
        val item = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return MyViewHolderCart(item)
    }

    override fun getItemCount(): Int {
        val data = goods
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolderCart, position: Int) {
        holder.bind(goods[position])
    }

}