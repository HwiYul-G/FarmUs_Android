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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.regex.Pattern

class FindpwActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivityFindPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        // 입력칸 관련 value 설정
        val editTextID : EditText = signupBinding.emailTextField

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
            val returnIntent = Intent(this, LoginActivity::class.java).apply{
                putExtra("PW_Resent", "임시 비밀번호가 이메일로 발송되었습니다.")
            }
            setResult(RESULT_OK, returnIntent)
            if(!isFinishing) finish()
        }

    }
}