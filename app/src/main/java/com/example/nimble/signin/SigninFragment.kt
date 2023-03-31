package com.example.nimble.signin


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimble.*
import com.example.nimble.api.LoginReceiveRemote
import com.example.nimble.api.api
import com.example.nimble.databinding.FragmentSigninBinding
import com.example.nimble.user.tokenUser
import kotlinx.coroutines.*



class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)
        binding.signinButton.setOnClickListener { signin() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_signinFragment_to_signupFragment)
        }

    }


    private fun signin() {
        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val response = api.signin(LoginReceiveRemote(email, password))
            val massege = response.errorBody()?.string()

            requireActivity().runOnUiThread{
                binding.errorMassage.text = massege
                val user = response.body()
                val token = response.body()?.token

                if(user != null){
                    tokenUser = token
                    MAIN.navController.navigate(R.id.miAccount)
                    Log.d("MAIN", "$token")
                }
            }
        }
    }

}

