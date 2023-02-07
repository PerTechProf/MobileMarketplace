package com.example.nimble.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimble.R
import com.example.nimble.api.ApiRequest
import com.example.nimble.api.BASE_URL
import com.example.nimble.api.CatalogGoods
import kotlinx.android.synthetic.main.fragment_catalog.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class CatalogFragment : Fragment() {

    val list = mutableListOf<CatalogGoods>(
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),)


    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .create(ApiRequest::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AdapterCatalog()

        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.setData(list)


    }

}