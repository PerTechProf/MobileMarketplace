package com.example.nimble.launch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nimble.R

class LaunchActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch3)
    }

    fun next(view: View) {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}