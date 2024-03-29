package com.sunidhi.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.Intent
import android.widget.Toast

class SignupActivity : AppCompatActivity() {

    lateinit var signup: Button
    lateinit var loginRedirect: TextView
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        databaseHelper = DatabaseHelper(this)

        signup = findViewById(R.id.signupButton)
        signup.setOnClickListener {
            // You might want to get the username and password from the user input
            val username = findViewById<EditText>(R.id.signupUsername).text.toString()
            val password = findViewById<EditText>(R.id.signupPassword).text.toString()
            signupDatabase(username, password)
        }

        loginRedirect = findViewById(R.id.loginRedirect)
        loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signupDatabase(username:String, password:String){
        val insertedRowId = databaseHelper.insertUser(username,password)
        if (insertedRowId != -1L){
            Toast.makeText(this,"Signup Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Signup Failed!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}