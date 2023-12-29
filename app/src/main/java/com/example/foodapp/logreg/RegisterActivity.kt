package com.example.foodapp.logreg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseDatabase: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuth: FirebaseAuth
    private var imageUri: Uri?=null
    private var address:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        firebaseDatabase= FirebaseDatabase.getInstance().getReference("user")

        binding.registerBtn.setOnClickListener(){
            var username=binding.registerUsernameInput.text.toString().trim()
            var email=binding.registerEmail.text.toString().trim()
            var password=binding.registerPasswordInput.text.toString().trim()
            if(email.isEmpty()){
                binding.registerEmail.error="email tidak boleh kosong"
                binding.registerEmail.requestFocus();
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.registerEmail.error="email tidak valid"
                binding.registerEmail.requestFocus();
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.registerPasswordInput.error="password tidak valid"
                binding.registerPasswordInput.requestFocus();
                return@setOnClickListener
            }
            registerUser(email,password,username);
        }
    }

    private fun registerUser(email: String, password: String,username:String) {
        firebaseDatabase= FirebaseDatabase.getInstance().getReference("user")
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val user = hashMapOf<String,Any>(
                    "username" to username,
                    "email" to email,
                    "password" to password
                )
                Toast.makeText(this@RegisterActivity,"Sukses", Toast.LENGTH_SHORT).show()
                firebaseDatabase.child(firebaseAuth.currentUser!!.uid).setValue(user)
                Intent(this,LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
            .addOnFailureListener(){
                Toast.makeText(this@RegisterActivity,it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}
