package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var signUp: Button
    private lateinit var signIn: TextView
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        signIn = findViewById(R.id.sign_in)
        signUp = findViewById(R.id.sign_up)

        signIn.setOnClickListener {
            val intent = Intent(this@SignUp, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@SignUp, "Enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@SignUp, "Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@SignUp, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@SignUp, "SignUp Successfully.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUp, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@SignUp, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
                })

        }
    }
}
