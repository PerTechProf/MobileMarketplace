package com.example.nimble.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.databinding.FragmentSigninBinding
import com.example.nimble.databinding.FragmentSignupBinding
import com.example.nimble.signup.SignupFragment


class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupButton.setOnClickListener{
            MAIN.navController.navigate(R.id.action_miAccount_to_signupFragment)
        }
    }

}
