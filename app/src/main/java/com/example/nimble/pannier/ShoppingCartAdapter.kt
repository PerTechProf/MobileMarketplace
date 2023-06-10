package com.example.nimble.pannier


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nimble.R
import com.example.nimble.api.Carts
import com.example.nimble.api.api
import kotlinx.coroutines.*


class ShoppingCartAdapter : RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolderCart>(){

    private var goods = listOf<Carts>()

    fun setList(list: List<Carts>){
        this.goods = list
        notifyDataSetChanged()
    }

    fun removeItem(id: Int){
        this.goods = this.goods.filter { it.id != id }
        notifyDataSetChanged()
    }



    class MyViewHolderCart(val view: View, val adapter: ShoppingCartAdapter) : RecyclerView.ViewHolder(view) {


        fun bind(cartItem: Carts) {

            val logo = view.findViewById<ImageView>(R.id.logo_cart)
            val nameItem = itemView.findViewById<TextView>(R.id.nameItem_cart)
            val priceStaff = itemView.findViewById<TextView>(R.id.price_staff_cart)
            val quantity = itemView.findViewById<TextView>(R.id.quantity)
            val deleteButton = itemView.findViewById<ImageButton>(R.id.cart_product_delete_btn)

            Glide.with(view.context).load(cartItem.product.logo).centerCrop().into(logo)
            nameItem.text = cartItem.product.name
            priceStaff.text = cartItem.product.price.toString()
            quantity.text = cartItem.quantity.toString()

            deleteButton.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch{
                    api.deleteCartItem(cartItem.id)
                    adapter.removeItem(cartItem.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCart{
        val item = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return MyViewHolderCart(item, this)
    }

    override fun getItemCount(): Int {
        return goods.size
    }

    override fun onBindViewHolder(holder: MyViewHolderCart, position: Int) {
        holder.bind(goods[position] )
    }

}