package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityLoginMainBinding
import com.example.farmus_application.ui.StartActivity
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.loginStartToolbar.toolbarMainTitleText.text = "시작화면"

        // 뒤로가기
        loginBinding.loginStartToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            val back_intent = Intent(this,StartActivity::class.java)
            if(!isFinishing) finish()
        }

        // 입력칸 관련 value 설정
        val editTextID : EditText = loginBinding.idTextField
        val editTextPW : EditText = loginBinding.pwTextField

        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부
        editTextID.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        loginBinding.idWarningMessage.visibility = View.VISIBLE
                        loginBinding.loginButton.isEnabled = false
                    } else if (editTextPW!=null && editTextPW.toString() != ""){
                        loginBinding.idWarningMessage.visibility = View.INVISIBLE
                        loginBinding.loginButton.isEnabled = true
                    }
                } else {
                    loginBinding.idWarningMessage.visibility = View.INVISIBLE
                }
            }
        })

        // 두 입력칸에 내용이 있을 경우에만 로그인 버튼 활성화
        editTextPW.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    if(loginBinding.idWarningMessage.visibility == View.INVISIBLE){
                        loginBinding.loginButton.isEnabled = true
                    }
                } else {
                    loginBinding.loginButton.isEnabled = false
                }
            }
        })

//        inner class LoginEditWatcher : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable?) {
//                if(s!=null && s.toString() != ""){
//
//                }
//            }
//        }

        // 아이디 찾기 이동
        val findID_intent = Intent(this, FindidActivity::class.java)
        loginBinding.signupButton.setOnClickListener{startActivity(findID_intent)}

        // 비밀번호 찾기 이동
        val findPW_intent = Intent(this, FindpwActivity::class.java)
        loginBinding.signupButton.setOnClickListener{startActivity(findPW_intent)}

        // 회원가입 이동 (이전 액티비티에 따라서 툴바의 내용 다르게 변화)
        val signup_intent = Intent(this, SignupActivity::class.java)
        loginBinding.signupButton.setOnClickListener{startActivity(signup_intent)}
    }
}