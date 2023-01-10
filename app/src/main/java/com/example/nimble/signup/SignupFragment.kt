package com.example.nimble.signup

import android.os.Bundle
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.view.FocusFinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.activity_signin.view.*
import kotlinx.android.synthetic.main.fragment_signin.view.*
import kotlinx.android.synthetic.main.fragment_signin.view.passwordEdit
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import java.util.regex.Pattern


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    private val serverUrl = "ws://0.0.0.0:8086/socketServer/"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun validateFullName(): Boolean{
        var errorMassage: String? = null
        val value: String = binding.firstNameEdit.text.toString()
        if(value.isEmpty()){
            errorMassage = "Full name is required"
        }

        if(errorMassage != null){
            binding.firstNameEdit.apply {
                error = errorMassage
            }
        }
        return errorMassage == null
    }


    private fun validateEmail(): Boolean{
        var error: String? = null
        val value = binding.emailEdit.text.toString()
        if(value.isEmpty()){
            error = "Email уже зарегистрирован"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            error = "Email address is invalid"
        }
        return error == null
    }

    private fun validateConfirmPassword(): Boolean{
        var error: String? = null
        val value = binding.passwordEditRegist.toString()
        if(value.isEmpty()){
            error = "Confirm password is required"
        }else if(value.length < 5){
            error = "Confirm password must be 5 characters long"
        }
        return error == null
    }


//    override fun onFocusChange(view: View?, hasFocus: Boolean){
//        if(view != null){
//            when(view.id){
//                R.id.firstNameEdit -> {
//                    if(hasFocus){
//
//                    }else{
//                        validateFullName()
//                    }
//                }
//                R.id.lastNameEdit -> {
//                    if(hasFocus){
//
//                    }else{
//                        validateFullName()
//                    }
//                }
//                R.id.emailEdit -> {
//                    if(hasFocus){
//
//                    }else{
//                        validateEmail()
//                    }
//                }
//                R.id.passwordEdit -> {
//                    validateConfirmPassword()
//                }
//            }
//        }
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener{
            MAIN.navController.navigate(R.id.action_signupFragment_to_miAccount)
        }
    }

}