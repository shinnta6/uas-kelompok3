package com.example.foodapp.logreg

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.MainActivity
import com.example.foodapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.register.setOnClickListener(){
            Intent(this,RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.loginBtn.setOnClickListener(){
            var email=binding.loginEmail.text.toString().trim();
            var password=binding.loginPasswordInput.text.toString().trim();
            if(email.isEmpty()){
                binding.loginEmail.error="email kosong"
                binding.loginEmail.requestFocus();
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error="email tdk sah"
                binding.loginEmail.requestFocus();
                return@setOnClickListener
            }
            if(password.isEmpty()){
                binding.loginPasswordInput.error="password salah"
                binding.loginPasswordInput.requestFocus();
                return@setOnClickListener
            }
            loginuser(email,password);
        }
    }

    private fun loginuser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                var intent= Intent(this, MainActivity::class.java);
                intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"${it.exception?.message}", Toast.LENGTH_SHORT).show();
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser !=null){
            var intent= Intent(this,MainActivity::class.java);
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK;
            startActivity(intent)
            finish()
        }
    }
}

