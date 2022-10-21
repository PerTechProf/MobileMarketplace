package com.example.nimble.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nimble.R
import com.example.nimble.databinding.FragmentSigninBinding
import com.example.nimble.signup.SignupFragment


class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):

            View? {

        val bind = FragmentSigninBinding.inflate(layoutInflater)
        val signupFragment = SignupFragment()

        bind.signupButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_view, signupFragment, SignupFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return bind.root
    }

}
