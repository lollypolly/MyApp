package com.example.myapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new.*

class Privat : AppCompatActivity() {
    var emailText: TextView? = null
    var fioText: TextView? = null
    var exitButton: Button? = null
    var photo: ImageView? = null
    val APP = "user"
    lateinit var file: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privat)
        file = getSharedPreferences(APP, Context.MODE_PRIVATE)

        emailText = findViewById(R.id.email)
        fioText = findViewById(R.id.fio)
        exitButton = findViewById(R.id.exit)
        photo = findViewById(R.id.photo)

        exitButton!!.setOnClickListener { exit() }
        emailText!!.text = file.getString("email", "")
        fioText!!.text = file.getString("name", "")
        photo!!.setImageDrawable(getDrawable(file.getInt("photo", 0)))


    }

    private fun exit() {
        val editor = file.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
