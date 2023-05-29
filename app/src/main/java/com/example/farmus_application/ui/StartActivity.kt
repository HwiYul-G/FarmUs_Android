package com.example.farmus_application.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityLoginStartBinding
import com.example.farmus_application.ui.login.LoginActivity
import com.example.farmus_application.ui.login.SignupActivity
import com.example.farmus_application.ui.login.TermsActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class StartActivity : AppCompatActivity() {

    private lateinit var startBinding: ActivityLoginStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityLoginStartBinding.inflate(layoutInflater)
        setContentView(startBinding.root)


        val login_intent = Intent(this, LoginActivity::class.java)
        startBinding.toLoginButton.setOnClickListener{
            startActivity(login_intent)
            if(!isFinishing) finish()
        }

        val signup_intent = Intent(this, TermsActivity::class.java)
        startBinding.signupButton.setOnClickListener{
            startActivity(signup_intent)
            if(!isFinishing) finish()
        }
    }
}