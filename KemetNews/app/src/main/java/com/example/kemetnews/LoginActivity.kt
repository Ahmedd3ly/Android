package com.example.kemetnews

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import android.os.Bundle

import android.widget.TextView
import android.content.Intent
import android.view.View

import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {
    private var inputUsername: EditText? = null
    private var inputPassword: EditText? = null
    private var loginButton: MaterialButton? = null
    private var regUsername: String? = null
    private var regPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.login_Button)
        inputUsername = findViewById(R.id.username)
        inputPassword = findViewById(R.id.password)

        val signUp = findViewById<TextView>(R.id.sign_up)
        val intent = intent

        regUsername = intent.getStringExtra("username")
        regPassword = intent.getStringExtra("password")

        signUp.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        }
        loginButton?.setOnClickListener { view: View -> checkCredentials(view) }
    }

    private fun checkCredentials(view: View) {
        val username = inputUsername!!.text.toString()
        val password = inputPassword!!.text.toString()
        if (username.isEmpty()) {
            showError(inputUsername, "Username is required!")
        } else if (username == "admin" && password == "admin") {
            Snackbar.make(view, "Login Successful!", Snackbar.LENGTH_LONG).show()
            loginButton!!.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                )
            }
        } else if (password.isEmpty() || password.length <= 7) {
            showError(inputPassword, "Password must be at least 8 characters!")
        } else if (username != regUsername || password != regPassword) {
            showError(inputUsername, "Username is invalid!")
        } else {
            Snackbar.make(view, "Login Successful!", Snackbar.LENGTH_LONG).show()
            loginButton!!.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }

    private fun showError(input: EditText?, s: String) {
        input!!.error = s
        input.requestFocus()
    }
}