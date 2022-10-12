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

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        sp = getSharedPreferences( "APP_SETTINGS", Context.MODE_PRIVATE)
        val handler = Handler()
        handler.postDelayed({start()}, 3000)
    }

    fun start(){

        val flag = sp.getBoolean("flag", false)
        if(!flag){
            val editor = sp.edit()
            editor.putBoolean("flag", true)
            editor.apply()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

    }

    fun signup(view: View) {
        val intent = Intent( this, SignupActivity::class.java)
        startActivity(intent)
    }

}