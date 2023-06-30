package com.example.farmus_application.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindPwBinding
import androidx.activity.viewModels
import com.example.farmus_application.viewmodel.findPW.FindPasswordViewModel
import java.util.regex.Pattern

class FindpwActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivityFindPwBinding
    private val findPasswordViewModel : FindPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        // 입력칸 관련 value 설정
        val editTextID : EditText = signupBinding.emailTextField

        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        signupBinding.findpwFirstToolbar.toolbarMainTitleText.text = "비밀번호 찾기"

        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        signupBinding.idWarningMessage.visibility = View.VISIBLE
                        signupBinding.sendTempPwButton.isEnabled = false
                    } else {
                        signupBinding.idWarningMessage.visibility = View.INVISIBLE
                        signupBinding.sendTempPwButton.isEnabled = true
                    }
                } else {
                    signupBinding.idWarningMessage.visibility = View.INVISIBLE
                    signupBinding.sendTempPwButton.isEnabled = false
                }
            }
        })

        signupBinding.sendTempPwButton.setOnClickListener {

            findPasswordViewModel.findPassword(editTextID.text.toString())
            if (findPasswordViewModel.findPasswordResponse.value?.result == true){
                val returnIntent = Intent(this, LoginActivity::class.java).apply{
                    putExtra("PW_Resent", "임시 비밀번호가 이메일로 발송되었습니다.")
                    Log.d("FindPWActivity", "임시 비밀번호 발송 성공")
                }
                setResult(RESULT_OK, returnIntent)
            }else{
                val returnIntent = Intent(this, LoginActivity::class.java).apply{
                    putExtra("PW_Resent", "이메일이 존재하지 않습니다. 이메일을 다시 확인해주세요.")
                    Log.d("FindPWActivity", "임시 비밀번호 발송 실패")
                }
                setResult(RESULT_OK, returnIntent)
            }

            if(!isFinishing) finish()
        }

    }
}