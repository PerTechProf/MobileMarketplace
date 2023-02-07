package com.example.nimble.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.nimble.*
import com.example.nimble.api.ApiRequest
import com.example.nimble.api.BASE_URL
import com.example.nimble.api.LoginReceiveRemote
import com.example.nimble.databinding.FragmentSigninBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)
        binding.signinButton.setOnClickListener { signin() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_miAccount_to_signupFragment)
        }

    }

    private fun signin() {
        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
            .create(ApiRequest::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.signin(LoginReceiveRemote(email, password))
                Log.d("MAIN", "Response: $response")
                withContext(Dispatchers.Main){
                    val token = response.body()?.token
                    if (token == null) {
                        Toast.makeText(activity, "Пользователь $email отсутствует", Toast.LENGTH_SHORT).show()
                    }else{
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(activity, "Добро пожаловать $email", Toast.LENGTH_SHORT).show()
                    }
                }

            }catch (e: Exception){
                Log.e("MAIN", "Error: ${e.message}")
            }
        }
    }

}

