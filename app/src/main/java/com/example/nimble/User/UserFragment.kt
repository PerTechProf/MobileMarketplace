package com.example.nimble.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exitAcc.setOnClickListener{

            MAIN.navController.navigate(R.id.action_miAccount_to_signinFragment)
            tokenUser = null
        }

        if(tokenUser == null){
            Toast.makeText(requireContext(),"Сначала требуется зарегистрироваться или войти", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.signinFragment, null)
        }
    }

}