package com.farm.farmus_application.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.farm.farmus_application.databinding.ActivityLoginStartBinding
import com.farm.farmus_application.ui.login.LoginActivity
import com.farm.farmus_application.ui.login.TermsActivity

class StartActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance: StartActivity? = null
        fun getInstance(): StartActivity? {
            return instance
        }
    }

    private lateinit var startBinding: ActivityLoginStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityLoginStartBinding.inflate(layoutInflater)
        setContentView(startBinding.root)

        startBinding.toLoginButton.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
//            if(!isFinishing) finish()
        }

        val signup_intent = Intent(this, TermsActivity::class.java)
        startBinding.signupButton.setOnClickListener{
            startActivity(signup_intent)
//            if(!isFinishing) finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("StartActivityDestroy","StartActivityDestroy")
    }
}