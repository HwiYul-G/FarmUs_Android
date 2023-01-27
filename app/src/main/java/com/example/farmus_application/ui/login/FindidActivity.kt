package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindIdBinding
import com.example.farmus_application.ui.StartActivity

class FindidActivity : AppCompatActivity() {

    private lateinit var findIdBinding: ActivityFindIdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findIdBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(findIdBinding.root)

        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        findIdBinding.findidFirstToolbar.toolbarMainTitleText.text = "시작화면"

        // 추후 이전 화면에 따른 뒤로가기 연결 페이지 변화 필요
        // 뒤로가기
        findIdBinding.findidFirstToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            val back_intent = Intent(this,StartActivity::class.java)
            if(!isFinishing) finish()
        }

    }
}