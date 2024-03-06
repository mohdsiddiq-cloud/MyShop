package com.siddiq.android.clothbazaar.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.databinding.ActivityLoginBinding
import com.siddiq.android.clothbazaar.databinding.ActivityRegisterBinding
import com.siddiq.android.clothbazaar.helper.SessionManager
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=Firebase.auth
        binding.registerPageText.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signIn(view: View) {
        hideKeyboard()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        if(email.isEmpty()){
            Toast.makeText(this, "Email Required", Toast.LENGTH_SHORT).show()
            binding.editTextEmail.error="Email Required"
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextEmail.error = null
            }
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Password Required", Toast.LENGTH_SHORT).show()
            binding.editTextPassword.error="Password Required"
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextPassword.error = null
            }
        }
        else{
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Access granted!", Toast.LENGTH_SHORT).show()
                    SessionManager.setLogin(this, true)
                    val intent = Intent(this, ShopActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    binding.editTextPassword.text.clear()
                }
            }
        }
    }
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}