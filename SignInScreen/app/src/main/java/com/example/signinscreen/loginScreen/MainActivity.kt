package com.example.signinscreen.loginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.signinscreen.R
import com.example.signinscreen.databinding.ActivityMainBinding
import signUpScreen.SignUpActivity
import signUpScreen.SiteActivity

class MainActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginPresenter? = null
    private val ERROR_LOGIN = "This login does not exist. Please try again !"
    private val ERROR_PASSWORD = "You have typed a wrong password !!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = LoginPresenter()
        presenter?.onAttach(this)


        binding.signUpButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener{
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            presenter?.onLogin(login,password)
        }

        binding.login.setOnFocusChangeListener{view, hasFocus ->
            if (!hasFocus) {
                val login = binding.login.text.toString()
                presenter?.checkLogin(login)
            }
        }

    }

    override fun setSuccess() {
        val intent = Intent(this, SiteActivity::class.java)
        startActivity(intent)
    }

    override fun setLoginError() {
        binding.errorContainer.visibility = View.VISIBLE
        binding.errorMessage.setText(ERROR_LOGIN, TextView.BufferType.EDITABLE)
    }

    override fun setOneMoreLogin() {
        binding.errorContainer.visibility = View.GONE
    }

    override fun setPasswordError() {
        binding.errorContainer.visibility = View.VISIBLE
        binding.forgetTextview.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.errorMessage.setText(ERROR_PASSWORD, TextView.BufferType.EDITABLE)
    }

    override fun setLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.signUpButton.visibility = View.GONE
    }
}
