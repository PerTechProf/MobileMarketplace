package com.example.nimble.catalog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.api.CartItem
import com.example.nimble.api.Good
import com.example.nimble.api.LoginReceiveRemote
import com.example.nimble.api.api
import com.example.nimble.databinding.FragmentProductBinding
import com.example.nimble.user.tokenUser
import com.example.nimble.user.userId
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.staff_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    private var vendorCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductBinding.inflate(layoutInflater, container, false)

        val logo = arguments?.getString("logo")
        val name = arguments?.getString("name")
        val price = arguments?.getDouble("price")
        val rating = arguments?.getDouble("rating")
        val description = arguments?.getString("description")
        val specification = arguments?.getString("specification")
        vendorCode = arguments?.getString("vendorCode")

        activity?.let { Glide.with(it).load(logo).centerCrop().into(binding.logo) }
        binding.name.text = name ?: ""
        binding.priceStaff.text = price?.toString() ?: ""
        binding.rating.rating = (rating?.toFloat() ?: "") as Float
        binding.description.text = description ?: ""
        binding.specification.text = specification ?: ""


        binding.addCart.setOnClickListener { addCart() }

        return binding.root
    }

    private fun addCart(){
        if(tokenUser == null && userId == null) {
            Toast.makeText(
                requireContext(), "Сначала требуется зарегистрироваться или войти",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val response = userId?.let { api.addCartItem(CartItem(
                userId = it,
                productId = vendorCode?: ""
            )) }



        }

    }





}