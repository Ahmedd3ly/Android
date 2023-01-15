package com.example.kemetnews

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import android.os.Bundle

import android.widget.TextView
import android.content.Intent
import android.view.View

import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private var inputUsername: EditText? = null
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var inputConfirmPassword: EditText? = null
    private var registerButton: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton = findViewById(R.id.register_Button)
        inputUsername = findViewById(R.id.username)
        inputEmail = findViewById(R.id.email)
        inputPassword = findViewById(R.id.password)
        inputConfirmPassword = findViewById(R.id.confirm_password)

        val haveAccount = findViewById<TextView>(R.id.have_account)
        haveAccount.setOnClickListener {
            startActivity(
                Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                )
            )
        }
        registerButton?.setOnClickListener { v -> checkCredentials(v) }
    }

    private fun checkCredentials(view: View) {
        val username = inputUsername!!.text.toString()
        val email = inputEmail!!.text.toString()
        val password = inputPassword!!.text.toString()
        val confirmPassword = inputConfirmPassword!!.text.toString()

        if (username.isEmpty()) {
            showError(inputUsername, "Username is required!")
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email is invalid!")
        } else if (password.isEmpty() || password.length <= 7) {
            showError(inputPassword, "Password must be 8 characters!")
        } else if (confirmPassword.isEmpty() || confirmPassword != password) {
            showError(inputConfirmPassword, "Passwords don't match!")
        } else {
            sendCredentials(username, password)
            registerButton!!.setOnClickListener {
                startActivity(
                    Intent(
                        this@RegisterActivity,
                        LoginActivity::class.java
                    )
                )
            }
            Snackbar.make(
                view, "Welcome " + username +
                        " your account has been created Successfully!", Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun sendCredentials(username: String, password: String) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("username", username)
        intent.putExtra("password", password)
        startActivity(intent)
    }

    private fun showError(input: EditText?, s: String) {
        input!!.error = s
        input.requestFocus()
    }
}