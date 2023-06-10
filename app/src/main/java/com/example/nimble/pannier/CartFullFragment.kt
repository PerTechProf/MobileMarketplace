package com.example.nimble.pannier

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nimble.R
import com.example.nimble.api.Good
import com.example.nimble.api.api
import com.example.nimble.user.userId
import kotlinx.android.synthetic.main.fragment_cart_full.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame


class CartFullFragment : Fragment() {

    private val adapter = ShoppingCartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_cart_full, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getGoods().invokeOnCompletion {
            if(adapter.itemCount == 0){
                val emptyFragment = CartIsEmptyFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, emptyFragment)
                    .commit()
                return@invokeOnCompletion
            }

        }



    }

    private fun setupRecyclerView(){
        recyclerViewCart.adapter = adapter
        recyclerViewCart.layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun getGoods(): Job {
        return GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.getCartItems(userId?: throw Exception("UserId is null"))
                Log.d("MAIN", "Response: $response")
                withContext(Dispatchers.Main){
                    val data = response.body()
                    if(data != null) {
                        adapter.setList(data.items)
                    }
                }
            }catch (e: Exception){
                Log.e("MAIN", "Error: ${e.message}")
            }
        }
    }


}