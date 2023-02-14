package com.example.nimble.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nimble.databinding.FragmentProductBinding


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductBinding.inflate(layoutInflater, container, false)

        val name = arguments?.getString("name")
        val price = arguments?.getDouble("price")
        val logo = arguments?.getString("logo")
        val description = arguments?.getString("description")
        val specification = arguments?.getString("specification")
        binding.name.text = name ?: ""
        binding.description.text = description ?: ""
        activity?.let { Glide.with(it).load(logo).centerCrop().into(binding.logo) }
        binding.priceStaff.text = price?.toString() ?: ""
        binding.specification.text = specification ?: ""


        return binding.root
    }


}