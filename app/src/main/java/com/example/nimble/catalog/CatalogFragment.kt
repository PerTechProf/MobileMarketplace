package com.example.nimble.catalog


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nimble.BASE_URL
import com.example.nimble.R
import com.example.nimble.api.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class CatalogFragment : Fragment() {

    private val adapter = AdapterCatalog()

    val api: ApiRequest = Retrofit.Builder()
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

        setupRecyclerView()
        getGoods()

    }

    private fun setupRecyclerView(){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun getGoods(){
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.getGoods()
                Log.d("MAIN", "Response: $response")
                withContext(Dispatchers.Main){
                    val data = response.body()
                    if(data != null) {
                        adapter.setData(data.items)
                    }
                }
            }catch (e: Exception){
                Log.e("MAIN", "Error: ${e.message}")
            }
        }
    }


}