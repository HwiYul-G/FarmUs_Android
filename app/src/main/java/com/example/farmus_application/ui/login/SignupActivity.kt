package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.farmus_application.databinding.ActivityMainBinding
import com.example.farmus_application.databinding.ActivitySignupFirstBinding

import com.example.farmus_application.databinding.FragmentSignupThirdBinding
import com.example.farmus_application.databinding.FragmentSignupFourthBinding
import com.example.farmus_application.databinding.FragmentSignupFifthBinding
import com.example.farmus_application.databinding.FragmentSignupSecondBinding
import com.example.farmus_application.ui.StartActivity
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupFirstBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        signupBinding.signupIdToolbar.toolbarMainTitleText.text = "시작화면"

        signupBinding.signupIdToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            val back_intent = Intent(this, StartActivity::class.java)
            if(!isFinishing) finish()
        }

        // 입력칸 관련 value 설정
        val editTextID : EditText = signupBinding.idTextField

        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        signupBinding.idWarningMessage.visibility = View.VISIBLE
                        signupBinding.toSecondSignupButton.isEnabled = false
                    } else {
                        signupBinding.idWarningMessage.visibility = View.INVISIBLE
                        signupBinding.toSecondSignupButton.isEnabled = true
                    }
                } else {
                    signupBinding.idWarningMessage.visibility = View.INVISIBLE
                }
            }
        })

        //회원가입 두번째 화면(프래그먼트) 추가
        signupBinding.toSecondSignupButton.setOnClickListener{
            supportFragmentManager
                .beginTransaction()
                .replace(signupBinding.signupMainLayout.id, SignupSecondFragment())
                .commitAllowingStateLoss()
        }

//        loginBinding.to_login_button.setOnClickListener{
//            supportFragmentManager
//                .beginTransaction()
//                .replace(binding.framefragment.id,FirstFragment())
//                .commitAllowingStateLoss()
//        }

    }
    // 프래그먼트 변화 클래스
//    private fun changeFrame(binding: ActivityMainBinding, fragment: Fragment){
//        supportFragmentManager.commit{
//            replace(binding.mainFragmentFrame.id, fragment)
//        }
//    }
}