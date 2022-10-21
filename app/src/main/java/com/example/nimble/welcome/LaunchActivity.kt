package com.example.nimble.welcome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.nimble.MainActivity
import com.example.nimble.R

class LaunchActivity : AppCompatActivity() {
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
        val handler = Handler()
        handler.postDelayed({start()}, 300)
        supportActionBar?.hide()
    }


    fun start(){
        val flag = sp.getBoolean("flag", false)
        if(!flag){
            val editor = sp.edit()
            editor.putBoolean("flag", true)
            editor.apply()
            val intent = Intent(this, WelcomeActivity1::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}