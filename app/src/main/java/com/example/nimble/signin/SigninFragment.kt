package com.example.nimble.signin


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.nimble.*
import com.example.nimble.api.LoginReceiveRemote
import com.example.nimble.api.api
import com.example.nimble.databinding.FragmentSigninBinding
import com.example.nimble.user.tokenUser
import com.example.nimble.user.userId
import kotlinx.coroutines.*



class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)

        val myButton = binding.signinButton
        val editEmail = binding.emailEdit

        editEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable){
                myButton.isEnabled = s.isNotEmpty()
            }
            override fun beforeTextChanged(s:CharSequence, start: Int, count: Int, after: Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

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

                if(user != null){
                    tokenUser = user.token
                    userId = user.userId
                    MAIN.navController.navigate(R.id.miAccount)
                    Log.d("MAIN", user.token)
                }
            }
        }
    }

}

