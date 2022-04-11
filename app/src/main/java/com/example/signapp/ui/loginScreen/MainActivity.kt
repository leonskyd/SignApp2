package com.example.signapp.ui.loginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.signapp.app
import com.example.signapp.ui.signUpScreen.SignUpActivity
import com.example.signapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginPresenter? = null
    private val ERROR_LOGIN = "This login does not exist. Please try again !"
    private val ERROR_PASSWORD = "You have typed a wrong password !!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = getPresenter()
        presenter?.onAttach(this)

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            presenter?.onLogin(login, password)
        }

        binding.loginEditText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val login = binding.loginEditText.text.toString()
                presenter?.checkLogin(login)
            }
        }
    }

    private fun getPresenter(): LoginPresenter? {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginInteractor)
    }

    override fun setSuccess() {
        val intent = Intent(this, SiteActivity::class.java)
        startActivity(intent)
    }

    override fun setLoginError() {
        binding.errorMessageContainer.visibility = View.VISIBLE
        binding.errorMessageEditText.setText(ERROR_LOGIN, TextView.BufferType.EDITABLE)
    }

    override fun setOneMoreLogin() {
        binding.errorMessageContainer.visibility = View.GONE
    }

    override fun setPasswordError() {
        binding.errorMessageContainer.visibility = View.VISIBLE
        binding.forgetTextview.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        binding.errorMessageEditText.setText(ERROR_PASSWORD, TextView.BufferType.EDITABLE)
    }

    override fun setLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.signUpButton.visibility = View.GONE
    }
}






