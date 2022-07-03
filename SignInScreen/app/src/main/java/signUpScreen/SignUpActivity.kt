package signUpScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.signinscreen.R
import com.example.signinscreen.databinding.ActivitySignUpBinding
import com.example.signinscreen.loginScreen.MainActivity

class SignUpActivity : AppCompatActivity(), SignUpView {

    private lateinit var binding: ActivitySignUpBinding
    private var presenter: SignUpPresenter? = null
    private val BUSY_LOGIN = "This login is already busy. Please try again !"
    private val NOT_CONFIRMED_PASSWORD = "Please confirm the password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SignUpPresenter()
        presenter?.onAttach(this)


        binding.saveButton.setOnClickListener{
            val login = binding.newLogin.text.toString()
            val password = binding.newPassword.text.toString()
            val confirmedPassword = binding.confirmPassword.text.toString()
            if(presenter?.isPasswordConfirmed(password,confirmedPassword)==true) {
                presenter?.onSignup(login, password)
                setSignedUp()
            } else {
                setPasswordNotConfirmed()
            }
        }

        binding.newLogin.setOnFocusChangeListener{view, hasFocus ->
            if (!hasFocus) {
                val login = binding.newLogin.text.toString()
                presenter?.checkNewLogin(login)
            } else {
                setOneMoreNewLogin()
            }
        }
    }


// Вьюшные методы

    override fun setSignedUp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun setLoginIsBusy() {
        binding.warningTextview.text = BUSY_LOGIN
        binding.warningTextview.visibility = View.VISIBLE

        binding.confirmPasswordContainer.visibility = View.GONE // сделано для того, чтобы юзер не продолжил вводить пароль при занятом логине
        // вместо этого хотел бы отключить возможность ввода в поля с паролями, но пока не понял как это делать.
    }

    override fun setPasswordNotConfirmed() {
        binding.warningTextview.text = NOT_CONFIRMED_PASSWORD
        binding.warningTextview.visibility = View.VISIBLE
    }

    override fun setOneMoreNewLogin() {
        binding.warningTextview.visibility = View.GONE
        binding.confirmPasswordContainer.visibility = View.VISIBLE // снова возвращаем поле для подтверждения пароля
    }
}