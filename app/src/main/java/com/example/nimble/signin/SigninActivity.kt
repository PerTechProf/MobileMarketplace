package com.example.nimble.signin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.nimble.R
import com.example.nimble.signup.SignupActivity

class SigninActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }

    fun signup(view: View) {
        val intent = Intent( this, SignupActivity::class.java)
        startActivity(intent)
    }

}