package com.example.nimble.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nimble.R



class WelcomeActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome1)

    }



    fun next(view: View) {

        val intent = Intent(this, WelcomeActivity2::class.java)
        startActivity(intent)

    }


}