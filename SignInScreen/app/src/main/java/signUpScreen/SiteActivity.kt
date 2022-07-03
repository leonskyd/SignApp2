package signUpScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signinscreen.R
import com.example.signinscreen.databinding.ActivitySiteBinding
import com.example.signinscreen.loginScreen.MainActivity

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