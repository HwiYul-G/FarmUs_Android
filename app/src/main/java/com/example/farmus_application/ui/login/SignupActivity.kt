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
import com.example.farmus_application.ui.StartActivity
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupFirstBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)

        // 시작화면으로 다시 이동
        signupBinding.signupIdToolbar.toolbarWithoutTitleBackButton.setOnClickListener(){
            BacktoTermsActivity()
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
                    signupBinding.toSecondSignupButton.isEnabled = false
                }
            }
        })

        //회원가입 두번째 화면(프래그먼트) 추가
        signupBinding.toSecondSignupButton.setOnClickListener{
            signupBinding.signupMainLayout.visibility = View.INVISIBLE
            signupBinding.signupMainLayout.isClickable = false
            signupBinding.signupMainLayout.isFocusable = false
            replaceFragment(2)
        }

    }
    // 프래그먼트 변화 클래스
    fun replaceFragment(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            2 -> {
                val editTextID : EditText = signupBinding.idTextField
                val bundle = Bundle()
                bundle.putString("idText", "${editTextID.text}")
                val S2Fragment = SignupSecondFragment()
                S2Fragment.arguments = bundle
                transaction.replace(signupBinding.signupFrameLayout.id, S2Fragment)
                transaction.addToBackStack("second")
            }
            3 -> {
                transaction.replace(signupBinding.signupFrameLayout.id, SignupThirdFragment())
                transaction.addToBackStack("third")
            }
            4 -> {
                transaction.replace(signupBinding.signupFrameLayout.id, SignupFourthFragment())
                transaction.addToBackStack("fourth")
            }
            5 -> {
                transaction.replace(signupBinding.signupFrameLayout.id, SignupFifthFragment())
                transaction.addToBackStack("fifth")
            }
        }
        transaction.commit()
        transaction.isAddToBackStackAllowed
    }
    // 회원가입 액티비티의 메인 레이아웃 다시 활성화
    fun activateMainLayout(){
        signupBinding.signupMainLayout.visibility = View.VISIBLE
        signupBinding.signupMainLayout.isClickable = true
        signupBinding.signupMainLayout.isFocusable = true
    }
    // 로그인 액티비티로 전환
    fun BacktoTermsActivity(){
//        val terms_intent = Intent(this, TermsActivity::class.java)
        if(!isFinishing) finish()
    }
}