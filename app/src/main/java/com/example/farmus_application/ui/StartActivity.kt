package com.example.farmus_application.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityLoginStartBinding
import com.example.farmus_application.ui.login.LoginActivity
import com.example.farmus_application.ui.login.SignupActivity

class StartActivity : AppCompatActivity() {

    private lateinit var startBinding: ActivityLoginStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityLoginStartBinding.inflate(layoutInflater)
        setContentView(startBinding.root)

        val login_intent = Intent(this, LoginActivity::class.java)
        startBinding.toLoginButton.setOnClickListener{startActivity(login_intent)}

        val signup_intent = Intent(this, SignupActivity::class.java)
        startBinding.signupButton.setOnClickListener{startActivity(signup_intent)}

//        supportFragmentManager
//            .beginTransaction()
//            .replace(loginBinding.framefragment.id,FirstFragment())
//            .commitAllowingStateLoss()
//
//        loginBinding.to_login_button.setOnClickListener{
//            supportFragmentManager
//                .beginTransaction()
//                .replace(binding.framefragment.id,FirstFragment())
//                .commitAllowingStateLoss()
//        }

    }
}