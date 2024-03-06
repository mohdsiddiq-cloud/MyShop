package com.siddiq.android.clothbazaar.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.databinding.ActivityRegisterBinding
import com.siddiq.android.clothbazaar.helper.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        db=Firebase.firestore
        binding.loginPageText.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signUp(view: View) {
        val name=binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val password2= binding.editTextPassword2.text.toString().trim()

        if(name.isEmpty()){
            Toast.makeText(this, "Name Required", Toast.LENGTH_SHORT).show()
            binding.editTextName.error="Name Required"
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextName.error = null
            }
        }

        else if(email.isEmpty()){
            Toast.makeText(this, "Email Required", Toast.LENGTH_SHORT).show()
            binding.editTextEmail.error="Email Required"
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextEmail.error = null
            }
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show()
            binding.editTextEmail.error="Enter valid Email"
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
        else if(password2.isEmpty()){
            Toast.makeText(this, "Password Required", Toast.LENGTH_SHORT).show()
            binding.editTextPassword2.error="Password Required"
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextPassword2.error = null
            }
        }

        else if(!password.equals(password2)){
            Toast.makeText(this, "Oops! Passwords differ.", Toast.LENGTH_SHORT).show()
            binding.editTextPassword2.error="Oops! Passwords differ."
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                binding.editTextPassword2.error = null
            }
            binding.editTextPassword.text.clear()
            binding.editTextPassword2.text.clear()
            binding.editTextPassword.requestFocus()
        }

        else{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                    val data= hashMapOf("name" to binding.editTextName.text.toString(),
                    "uid" to user?.uid)
                    if (user != null) {
                        db.collection("Users").document(user.uid).set(data)
                    }
                    SessionManager.setLogin(this, true)
                    val intent = Intent(this, ShopActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
