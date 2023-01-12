package com.example.nimble.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nimble.ApiRequest
import com.example.nimble.BASE_URL
import com.example.nimble.MAIN
import com.example.nimble.R
import com.example.nimble.databinding.FragmentSignupBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.net.Socket
import java.util.Scanner


class SignupFragment : Fragment(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    lateinit var binding: FragmentSignupBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        return binding.root

        binding.firstNameEdit.onFocusChangeListener = this
        binding.emailEdit.onFocusChangeListener = this
        binding.passwordEditRegist.onFocusChangeListener = this
        binding.conPasswordEdit.onFocusChangeListener = this

        binding.signupButton.setOnClickListener { signup() }


    }

    private fun validateFullName(): Boolean{
        var errorMassage: String? = null
        val value: String = binding.firstNameEdit.text.toString()
        if(value.isEmpty()){
            errorMassage = "Укажите полное имя."
        }

        if(errorMassage != null){
            binding.firstNameEdit.apply {
                error = errorMassage
            }
        }
        return errorMassage == null
    }

    private fun validatePassword(): Boolean{
        var error: String? = null
        val value = binding.passwordEditRegist.toString()
        if(value.isEmpty()){
            error = "Необходим пароль"
        }else if(value.length < 6){
            error = "Пароль должен состоять из 6 символов"
        }
        return error == null
    }

    private fun validateConfirmPassword(): Boolean{
        var error: String? = null
        val value = binding.conPasswordEdit.toString()
        if(value.isEmpty()){
            error = "Требуется подтверждение пароля"
        }else if(value.length < 6){
            error = "Подтверждение пароля должно состоять из 6 символов"
        }
        return error == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean{
        var error: String? = null
        val password = binding.passwordEditRegist.text.toString()
        val confirmPassword = binding.conPasswordEdit.text.toString()
        if(password != confirmPassword){
            error = "Пароли не совпадают"
        }
        return error == null
    }

    private fun validateEmail(): Boolean{
        var error: String? = null
        val value = binding.emailEdit.text.toString()
        if(value.isEmpty()){
            error = "Email уже зарегистрирован"
        }else if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            error = "Адрес электронной почты является недействительным"
        }
        return error == null
    }




    override fun onFocusChange(view: View?, hasFocus: Boolean){
        if(view != null){
            when(view.id){
                R.id.firstNameEdit -> {
                    if(hasFocus){
                        if(binding.firstNameEdit.isEnabled){
                            binding.firstNameEdit.error = false.toString()
                        }
                    }else{
                        validateFullName()
                    }
                }
                R.id.emailEdit -> {
                    if(hasFocus){
                        if(binding.emailEdit.isEnabled){
                            binding.emailEdit.error = false.toString()
                        }
                    }else{
                        validateEmail()
                    }
                }
                R.id.passwordEditRegist -> {
                    if(hasFocus){
                        if(binding.passwordEditRegist.isEnabled){
                            binding.passwordEditRegist.error = false.toString()
                        }
                    }else {
                        validatePassword()
                    }
                }

                R.id.conPasswordEdit -> {
                    if(hasFocus){
                        if(binding.conPasswordEdit.isEnabled){
                            binding.conPasswordEdit.error = false.toString()
                        }
                    }else {
                        validateConfirmPassword()
                    }
                }

            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener{
            MAIN.navController.navigate(R.id.action_signupFragment_to_miAccount)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        return false
    }

    fun signup(){
        val email = binding.emailEdit.text.toString()
        val firstName = binding.firstNameEdit.text.toString()
        val lastName = binding.lastNameEdit.text.toString()
        val password = binding.passwordEditRegist.text.toString()

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.singnup(email, password, firstName, lastName)
                Log.d("MAIN", "Response: $response")
            }catch (e: Exception){
                Log.e("MAIN", "Error: ${e.message}")
            }
        }
    }

}