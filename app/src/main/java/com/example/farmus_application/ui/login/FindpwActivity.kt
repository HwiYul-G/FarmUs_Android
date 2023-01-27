package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindPwBinding

class FindpwActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

    }
}