package com.example.nimble.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimble.BASE_URL
import com.example.nimble.R
import com.example.nimble.api.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class CatalogFragment : Fragment() {


    private val adapter = AdapterCatalog()

    val list = mutableListOf<CatalogGoods>(
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),
        CatalogGoods("Gforce", 200.23, "wdasda", "sdawdawd"),)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        getGoods()

    }



    private fun setupRecyclerView(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter.setData(list)
    }



    private fun getGoods(){
        Api.retrofitService.getGoods().enqueue(object : Callback<List<CatalogGoods>> {
            override fun onResponse(
                call: Call<List<CatalogGoods>>,
                response: Response<List<CatalogGoods>>
            ) {
                response.body()?.map {
                    adapter.setData(list)
                }
            }

            override fun onFailure(call: Call<List<CatalogGoods>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }


}