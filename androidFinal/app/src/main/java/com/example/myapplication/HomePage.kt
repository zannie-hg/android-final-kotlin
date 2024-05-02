package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference


class HomePage : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Không cần dòng này trong Kotlin
        // binding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(binding.root)
        initBanner()
    }

    private fun initBanner() {
        val myRef: DatabaseReference = database.getReference("Banner")

    }
}
