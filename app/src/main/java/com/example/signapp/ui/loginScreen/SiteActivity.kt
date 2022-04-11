package com.example.signapp.ui.loginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signapp.databinding.ActivitySiteBinding

class SiteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.comeBackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}