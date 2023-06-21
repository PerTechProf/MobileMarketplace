package com.example.nimble.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nimble.*
import com.example.nimble.api.ApiRequest
import com.example.nimble.api.RegisterReceiveRemote
import com.example.nimble.api.api
import com.example.nimble.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)

        emailFocusListener()
        validatePasswordFocusListener()
        validateConfirmPasswordFocusListener()
        validatePasswordAndConfirmPasswordFocusListener()
        firstNameFocusListener()
        lastNameFocusListener()
        binding.signupButton.setOnClickListener { signup() }

        return binding.root
    }

    private fun emailFocusListener()
    {
        binding.emailEdit.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validatePasswordFocusListener()
    {
        binding.passwordEditRegist.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.passwordContainer.helperText = validatePassword()
            }
        }
    }

    private fun validateConfirmPasswordFocusListener()
    {
        binding.passwordEditRegist.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.passwordContainer.helperText = validateConfirmPassword()
            }
        }
    }

    private fun validatePasswordAndConfirmPasswordFocusListener()
    {
        binding.conPasswordEdit.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.conPasswordContainer.helperText = validatePasswordAndConfirmPassword()
            }
        }
    }

    private fun firstNameFocusListener()
    {
        binding.firstNameEdit.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.firstNameContainer.helperText = validFirstName()
            }
        }
    }

    private fun lastNameFocusListener()
    {
        binding.lastNameEdit.setOnFocusChangeListener{ _, focused->
            if(!focused)
            {
                binding.lastNameContainer.helperText = validLastName()
            }
        }
    }

    private fun validFirstName(): String? {
        val firstNameText = binding.firstNameEdit.text.toString()
        if(firstNameText.isEmpty()){
            return "Укажите имя"
        }
        return null
    }

    private fun validLastName(): String? {
        val firstNameText = binding.lastNameEdit.text.toString()
        if(firstNameText.isEmpty()){
            return "Укажите фамилию"
        }
        return null
    }



    private fun validEmail(): String? {
        val emailText = binding.emailEdit.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Адрес электронной почты является недействительным"
        }else if(emailText.isEmpty()){
            return "Email уже зарегистрирован"
        }
        return null
    }

    private fun validatePassword(): String? {
        val passwordText = binding.passwordEditRegist.toString()
        if(passwordText.length < 6)
        {
            return "Пароль должен состоять из 6 символов"
        }
        return null
    }


    private fun validateConfirmPassword(): String? {
        val repasswordText = binding.conPasswordEdit.toString()
        if(repasswordText.isEmpty()){
           return "Требуется подтверждение пароля"
        }else if(repasswordText.length < 6){
            return "Подтверждение пароля должно состоять из 6 символов"
        }
        return null
    }

    private fun validatePasswordAndConfirmPassword(): String? {
        val password = binding.passwordEditRegist.text.toString()
        val confirmPassword = binding.conPasswordEdit.text.toString()
        if(password != confirmPassword){
            return "Пароли не совпадают"
        }
        return null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener{
            MAIN.navController.navigate(R.id.action_signupFragment_to_signinFragment)
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun signup(){
        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEditRegist.text.toString()
        val firstName = binding.firstNameEdit.text.toString()
        val lastName = binding.lastNameEdit.text.toString()

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.signup(RegisterReceiveRemote(email, password, firstName, lastName))
                Log.d("MAIN", "Response: $response")
            }catch (e: Exception){
                Log.e("MAIN", "Error: ${e.message}")
            }
        }
        Toast.makeText(activity, "Вы авторизованны!", Toast.LENGTH_SHORT).show()
        MAIN.navController.navigate(R.id.signinFragment)

    }

}