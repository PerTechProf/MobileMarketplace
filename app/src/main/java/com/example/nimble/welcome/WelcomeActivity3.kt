package com.example.nimble.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nimble.MainActivity
import com.example.nimble.R

class WelcomeActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome3)
    }

    fun next(view: View) {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}