package com.example.nimble.catalog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.nimble.api.Good
import com.example.nimble.databinding.FragmentProductBinding
import com.example.nimble.user.tokenUser


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding

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
        if(tokenUser != null ){
            val id = arguments?.getString("id")

        }else{
            Toast.makeText(requireContext(),"Сначала требуется зарегистрироваться или войти",
                Toast.LENGTH_SHORT).show()
        }

    }






}