package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var signIn: Button
    private lateinit var signUp: TextView
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        signIn = findViewById(R.id.sign_in)
        signUp = findViewById(R.id.sign_up)

        signUp.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUp::class.java)
            startActivity(intent)
            finish()
        }

        signIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@MainActivity, "Enter Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@MainActivity, "Enter Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@MainActivity, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, HomePage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
