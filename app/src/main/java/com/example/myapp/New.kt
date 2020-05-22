package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class New : AppCompatActivity() {
    var exitButton: Button?=null

    private fun back() {
        startActivity(Intent(this, MainActivity::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        exitButton = findViewById(R.id.exit)
        exitButton!!.setOnClickListener { back() }
    }
}
