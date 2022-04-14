package com.example.signapp.ui.loginScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.signapp.app
import com.example.signapp.data.AppState
import com.example.signapp.ui.signUpScreen.SignUpActivity
import com.example.signapp.databinding.ActivityMainBinding
import com.example.signapp.ui.LoginViewModel

class MainActivity : AppCompatActivity(), LoginView {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: LoginViewModel? = null

    private val ERROR_LOGIN = "This login does not exist. Please try again !"
    private val ERROR_PASSWORD = "You have typed a wrong password !!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel()
        //presenter?.onAttach(this)

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            val login = binding.loginEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel?.onLogin(login, password)?.observe(
                this, Observer <AppState>{state -> render(state)})
        }

       binding.loginEditText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val login = binding.loginEditText.text.toString()
                viewModel?.checkLogin(login)?.observe(
                    this, Observer <AppState>{state -> render(state)})
            }
        }
    }

    private fun getViewModel(): LoginViewModel? {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel?:LoginViewModel(app.loginInteractor)

    }
    private fun render(state: AppState?) {
        when (state) {
            is AppState.Success -> {setSuccess()}
            is AppState.PasswordError -> {setPasswordError()}
            is AppState.LoginError -> {setLoginError()}
            is AppState.oneMoreLogin ->{setOneMoreLogin()}
        }
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






