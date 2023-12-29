package com.example.foodapp.logreg

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodapp.databinding.ActivityLogRegBinding
import com.google.firebase.database.DatabaseReference

class LogRegActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogRegBinding
    lateinit var firebaseDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener(){
            Intent(this@LogRegActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.registerBtn.setOnClickListener(){
            Intent(this@LogRegActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}