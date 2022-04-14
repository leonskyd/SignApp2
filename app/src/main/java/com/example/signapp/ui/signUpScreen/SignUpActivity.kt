package com.example.signapp.ui.signUpScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.signapp.app
import com.example.signapp.data.AppState
import com.example.signapp.databinding.ActivitySignUpBinding
import com.example.signapp.ui.loginScreen.LoginPresenter
import com.example.signapp.ui.loginScreen.MainActivity

class SignUpActivity : AppCompatActivity(), SignUpView {

    private lateinit var binding: ActivitySignUpBinding
    private var viewModel: SignUpViewModel? = null
    private val BUSY_LOGIN = "This login is already busy. Please try again !"
    private val NOT_CONFIRMED_PASSWORD = "Please confirm the password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getSignUpViewModel()

        binding.saveButton.setOnClickListener {
            val login = binding.newLoginEditText.text.toString()
            val password = binding.newPasswordEditText.text.toString()
            val confirmedPassword = binding.confirmPasswordEditText.text.toString()
            if (viewModel?.isPasswordConfirmed(password, confirmedPassword) == true) {
                viewModel?.onSignup(login, password)
                setSignedUp()
            } else {
                setPasswordNotConfirmed()
            }
        }

        binding.newLoginEditText.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val login = binding.newLoginEditText.text.toString()
                viewModel?.checkNewLogin(login)?.observe(
                    this, Observer<AppState> { state -> render(state) })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppState.LoginBusy(false) }

    private fun render(state: AppState?) {
        when (state) {
            is AppState.OneMoreNewLogin -> {
                setOneMoreNewLogin()
            }
            is AppState.LoginBusy -> {
                setLoginIsBusy()
            }
        }
    }

    private fun getSignUpViewModel(): SignUpViewModel? {
        val viewModel = lastCustomNonConfigurationInstance as? SignUpViewModel
        return viewModel ?: SignUpViewModel(app.signUpInteractor)
    }

    override fun setSignedUp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun setLoginIsBusy() {
        binding.warningTextview.text = BUSY_LOGIN
        binding.warningTextview.visibility = View.VISIBLE

        binding.confirmPasswordContainer.visibility = View.GONE
    }

    override fun setPasswordNotConfirmed() {
        binding.warningTextview.text = NOT_CONFIRMED_PASSWORD
        binding.warningTextview.visibility = View.VISIBLE
    }

    override fun setOneMoreNewLogin() {
        binding.warningTextview.visibility = View.GONE
        binding.confirmPasswordContainer.visibility = View.VISIBLE
    }
}