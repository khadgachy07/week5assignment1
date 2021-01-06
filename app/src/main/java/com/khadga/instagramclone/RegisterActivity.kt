package com.khadga.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.khadga.instagramclone.models.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var etStudentID: EditText
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etProfilePicture: EditText
    private lateinit var spinnerBatch: Spinner
    private lateinit var btnRegister: Button
    var batch = ""
    val batches = listOf("24A", "24B", "25A", "25B", "25C", "25D")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvClickHereToLogin: TextView = findViewById(R.id.tvClickHereToLogin)
        etStudentID = findViewById(R.id.etCoventryID)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etProfilePicture = findViewById(R.id.etProfilePicture)
        spinnerBatch = findViewById(R.id.spinnerBatchName)
        btnRegister = findViewById(R.id.btnRegister)

        loadSpinner()

        tvClickHereToLogin.setOnClickListener {
            openLogin()
        }

        btnRegister.setOnClickListener {
            if (validate()) {
                DataStore.users.add(
                    User(
                        etStudentID.text.toString(),
                        etFirstName.text.toString(),
                        etLastName.text.toString(),
                        etUsername.text.toString().trim(),
                        etPassword.text.toString().trim(),
                        batch,
                        etProfilePicture.text.toString().trim()
                    )
                )
                Toast.makeText(this, "User Registration Successful", Toast.LENGTH_SHORT).show()
                openLogin()

//                Intent(this, MainActivity::class.java).also {
//                    startActivity(it)
//                }
            }

        }
    }
    private fun openLogin() {
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun loadSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            batches
        )
        spinnerBatch.adapter = adapter

        spinnerBatch.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    batch = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }

    private fun validate(): Boolean {
        when {
            TextUtils.isEmpty(etStudentID.text) -> {
                etStudentID.error = "Please Enter Your Student ID"
                etStudentID.requestFocus()
                return false
            }
            TextUtils.isEmpty(etFirstName.text) -> {
                etFirstName.error = "Please Enter the First Name"
                etFirstName.requestFocus()
                return false
            }
            TextUtils.isEmpty(etLastName.text) -> {
                etLastName.error = "Please Enter the Last Name"
                etLastName.requestFocus()
                return false
            }
            TextUtils.isEmpty(etUsername.text) -> {
                etUsername.error = "Please Enter the Username"
                etUsername.requestFocus()
                return false
            }
            TextUtils.isEmpty(etPassword.text) -> {
                etPassword.error = "Please Enter the Password"
                etPassword.requestFocus()
                return false
            }
            TextUtils.isEmpty(etProfilePicture.text) -> {
                etProfilePicture.error = "Please Enter the Profile Picture Link"
                etProfilePicture.requestFocus()
                return false
            }
        }
        return true
    }
}