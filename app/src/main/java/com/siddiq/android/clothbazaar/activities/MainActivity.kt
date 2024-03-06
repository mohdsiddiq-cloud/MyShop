package com.siddiq.android.clothbazaar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.helper.SessionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SessionManager.isLoggedIn(this)) {
            startActivity(Intent(this, ShopActivity::class.java))
            finish()
        } else {
            setContentView(R.layout.activity_main)
        }
    }

    fun loginPage(view: View) {
        val intent=Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun registerPage(view: View) {
        val intent=Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}