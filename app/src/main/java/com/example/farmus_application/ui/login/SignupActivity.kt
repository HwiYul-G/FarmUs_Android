package com.example.farmus_application.ui.login

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivitySignupFirstBinding
import com.example.farmus_application.viewmodel.login.SignUpViewModel
import java.util.regex.Pattern
import kotlin.math.sign

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupFirstBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupFirstBinding.inflate(layoutInflater)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        setContentView(signupBinding.root)

        // 시작화면으로 다시 이동
        signupBinding.signupIdToolbar.toolbarWithoutTitleBackButton.setOnClickListener(){
            BacktoTermsActivity()
        }

        signUpViewModel.isEmailVerificationSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                signupBinding.idWarningMessage.visibility = VISIBLE
                signupBinding.idWarningMessage.text = "사용가능한 이메일 입니다."
                signupBinding.idWarningMessage.setTextColor(ContextCompat.getColor(this, R.color.main))
                signupBinding.toSecondSignupButton.isEnabled = true
            } else {
                signupBinding.idWarningMessage.visibility = VISIBLE
                signupBinding.idWarningMessage.text = "중복된 이메일 입니다."
                signupBinding.toSecondSignupButton.isEnabled = false
            }
        }

        // 입력칸 관련 value 설정
        val editTextID = signupBinding.idTextField

        editTextID.setOnEditorActionListener { view, actionId, keyEvent ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // keyBoard 내리기
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editTextID.windowToken, 0)

                val editEmail = signupBinding.idTextField.text.toString()
                val pattern = Patterns.EMAIL_ADDRESS
                if (!pattern.matcher(editEmail).matches()) {
                    signupBinding.idWarningMessage.visibility = VISIBLE
                    signupBinding.idWarningMessage.text = "이메일 형식에 맞게 입력해주세요"
                } else {
                    // 아이디 중복체크 API 실행
                    signUpViewModel.emailVerification(editEmail)
                }
                handled = true
            }
            handled
        }

        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!=null && s.toString() != "") {
                    signupBinding.idTextFieldClear.visibility = VISIBLE
                } else {
                    signupBinding.idTextFieldClear.visibility = INVISIBLE
                    signupBinding.idWarningMessage.visibility = INVISIBLE
                }
            }
            override fun afterTextChanged(s: Editable?) {
//                if(s!=null && s.toString() != ""){
//                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
//                    if (!pattern.matcher(s).matches()) {
//                        signupBinding.idWarningMessage.visibility = View.VISIBLE
//                        signupBinding.toSecondSignupButton.isEnabled = false
//                    } else {
//                        signupBinding.idWarningMessage.visibility = View.INVISIBLE
//                        signupBinding.toSecondSignupButton.isEnabled = true
//                    }
//                } else {
//                    signupBinding.idWarningMessage.visibility = View.INVISIBLE
//                    signupBinding.toSecondSignupButton.isEnabled = false
//                }
            }
        })

        //회원가입 두번째 화면(프래그먼트) 추가
        signupBinding.toSecondSignupButton.setOnClickListener{
            signupBinding.signupMainLayout.visibility = View.INVISIBLE
            signupBinding.signupMainLayout.isClickable = false
            signupBinding.signupMainLayout.isFocusable = false
            val editTextId = signupBinding.idTextField.text.toString()
            val bundle = Bundle().apply {
                putString("idText",editTextId)
            }
            replaceFragment(2, bundle)
        }

    }
    // 프래그먼트 변화 클래스
    fun replaceFragment(int: Int, bundle: Bundle){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            2 -> {
                val signUpSecondFragment = SignupSecondFragment()
                signUpSecondFragment.arguments = bundle
                transaction.replace(signupBinding.signupFrameLayout.id, signUpSecondFragment)
                transaction.addToBackStack("second")
            }
            3 -> {
                val signupThirdFragment = SignupThirdFragment()
                signupThirdFragment.arguments = bundle
                transaction.replace(signupBinding.signupFrameLayout.id, signupThirdFragment)
                transaction.addToBackStack("third")
            }
            4 -> {
                val signupFourthFragment = SignupFourthFragment()
                signupFourthFragment.arguments = bundle
                transaction.replace(signupBinding.signupFrameLayout.id, signupFourthFragment)
                transaction.addToBackStack("fourth")
            }
            5 -> {
                val signupFifthFragment = SignupFifthFragment()
                signupFifthFragment.arguments = bundle
                transaction.replace(signupBinding.signupFrameLayout.id, signupFifthFragment)
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