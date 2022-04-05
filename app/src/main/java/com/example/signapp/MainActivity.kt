package com.example.signapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.signapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInButton.setOnClickListener{
            binding.progressBar.visibility = View.VISIBLE
            binding.signUpButton.visibility = View.GONE  // проверить как будет выглядеть экран при загрузке
        }


        binding.signUpButton.setOnClickListener{
            binding.errorContainer.visibility = View.VISIBLE
            binding.forgetTextview.visibility = View.VISIBLE // проверка как будет выгдяеть экран при ошибке
        }


    }


}