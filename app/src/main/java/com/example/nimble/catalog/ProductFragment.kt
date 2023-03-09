package com.example.nimble.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.example.nimble.databinding.FragmentProductBinding


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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


        return binding.root
    }


}