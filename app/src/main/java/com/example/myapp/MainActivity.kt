package com.example.myapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var emailText: EditText? = null
    var passwordText: EditText? = null
    var inButton: Button? = null
    var upButton: Button? = null
    var users = mutableListOf(User("polina.mokeeva@mail.ru", "Aa1234567", "Мокеева Полина Алексеевна",R.drawable.tes ))
    val APP = "user"
    lateinit var file: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        file = getSharedPreferences(APP, Context.MODE_PRIVATE)
        if (file.contains("name") )
            startActivity(Intent(this, Privat::class.java))

        emailText = findViewById(R.id.email)
        passwordText = findViewById(R.id.pass)
        inButton = findViewById(R.id.inner)
        upButton = findViewById(R.id.up)

        inButton!!.setOnClickListener { login() }
        upButton!!.setOnClickListener { new() }

    }

    private fun login() {
        var email = emailText?.text.toString()
        var pass = passwordText?.text.toString()
        var proverka = false
        if (Regex("([0-9a-zA-Z]+[\\.\\-_]?)*[0-9a-zA-Z]+@([0-9a-zA-Z]+[\\.\\-_]?)+\\.[0-9a-zA-Z]+").find(
                email
            ) != null
            && Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$").find(pass) != null
        ) {

            for (user in users) {
                if (email == user.email && pass == user.pass) {
                    proverka = true
                    val editor = file.edit()
                    editor.putInt("photo", user.photo)
                    editor.putString("email", email)
                    editor.putString("name", user.fio)
                    editor.apply()
                    editor.commit()
                    startActivity(Intent(this, Privat::class.java))
                    break
                }
            }
            if (proverka == false) {
                Toast.makeText(
                    this,
                    "Пароль или Электронная почта введены неправильно",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else Toast.makeText(this, "Неправильный формат данных", Toast.LENGTH_SHORT).show()
    }


    private fun new() {
        startActivity(Intent(this, New::class.java))
    }


}
