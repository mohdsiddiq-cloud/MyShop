package com.example.android.clothbazaar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.clothbazaar.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loginPage(view: View) {
        val intent=Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun registerPage(view: View) {
        val intent=Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}