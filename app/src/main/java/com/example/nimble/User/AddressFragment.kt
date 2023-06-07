package com.example.nimble.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimble.R
import com.example.nimble.api.UserAddress
import com.example.nimble.api.api
import com.example.nimble.databinding.FragmentAddressBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddressFragment : Fragment() {

    lateinit var binding: FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(layoutInflater, container, false)

        binding.registerAddressButton.setOnClickListener { registerAddress() }

        return inflater.inflate(R.layout.fragment_address, container, false)
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun registerAddress(){
        val homeAddress = binding.homeAddressEdit.toString()
        val houseAddress = binding.houseAddressEdit.toString()
        val cityAddress = binding.cityAddressEdit.toString()

        GlobalScope.launch(Dispatchers.IO) {
            try{
                val response = api.registerAddress(UserAddress( homeAddress, houseAddress, cityAddress))
                Log.d("MAIN", "Respond: $response")
            }catch (e:Exception){
                Log.e("MAIN", "Error ${e.message}")
            }
        }
    }

}