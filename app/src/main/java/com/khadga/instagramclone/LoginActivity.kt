package com.khadga.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername :EditText
    private lateinit var etPassword :EditText
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        val tvClickHereToRegister : TextView = findViewById(R.id.tvClickHereToRegister)
        tvClickHereToRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        btnLogin.setOnClickListener {
            if (validate()) {
                val username = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()

                for (user in DataStore.users) {
                    if (user.username == username && user.password == password) {
                        Log.d("msg", "${DataStore.users.size}")
                        Intent(this, MainActivity::class.java).also {
                            it.putExtra("id", user.coventryID)
                            startActivity(it)
                        }
                        return@setOnClickListener
                    }
                }
                Toast.makeText(this, "Either Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
                clear()
            }
        }
    }
    private fun clear() {
        etUsername.setText("")
        etPassword.setText("")
    }

    private fun validate() : Boolean {
        when {
            TextUtils.isEmpty(etUsername.text) -> {
                etUsername.error = "Enter Your Username"
                etUsername.requestFocus()
                return false
            }
            TextUtils.isEmpty(etPassword.text) -> {
                etPassword.error = "Enter Your Password"
                etPassword.requestFocus()
                return false
            }
        }
        return true
    }
}