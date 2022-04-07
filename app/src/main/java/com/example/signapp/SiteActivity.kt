package com.example.signapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signapp.databinding.ActivityMainBinding
import com.example.signapp.databinding.ActivitySiteBinding
import com.example.signapp.loginScreen.MainActivity

class SiteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backToLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}