package com.example.nimble.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nimble.R
import com.example.nimble.signin.SigninActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun signin(view: View) {
        val intent = Intent( this, SigninActivity::class.java)
        startActivity(intent)
    }

}