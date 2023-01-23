package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindPwBinding
import com.example.farmus_application.ui.StartActivity

class FindpwActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

//        signupBinding.signupIdToolbar.toolbarMainTitleText.text = "시작화면"
//
//        signupBinding.signupIdToolbar.toolbarWithTitleBackButton.setOnClickListener(){
//            val back_intent = Intent(this, StartActivity::class.java)
//            if(!isFinishing) finish()
//        }

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