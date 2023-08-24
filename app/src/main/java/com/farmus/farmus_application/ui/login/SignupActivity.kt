package com.farmus.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.farmus.farmus_application.R
import com.farmus.farmus_application.utilities.ValidationCheckUtil
import com.farmus.farmus_application.databinding.ActivitySignupFirstBinding
import com.farmus.farmus_application.viewmodel.login.SignUpViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var signupBinding: ActivitySignupFirstBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupFirstBinding.inflate(layoutInflater)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        setContentView(signupBinding.root)

        // 입력칸 관련 value 설정
        val editTextID = signupBinding.idTextField

        // 시작화면으로 다시 이동
        signupBinding.signupIdToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            BacktoTermsActivity()
        }

        signUpViewModel.isEmailVerificationSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                signupBinding.idWarningMessage.visibility = VISIBLE
                signupBinding.idWarningMessage.text = "사용가능한 이메일 입니다."
                signupBinding.idWarningMessage.setTextColor(ContextCompat.getColor(this, R.color.main))
                signupBinding.toSecondSignupButton.isEnabled = true

                // keyboard hide
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(signupBinding.idTextField.windowToken, 0)


            } else {
                signupBinding.idWarningMessage.visibility = VISIBLE
                signupBinding.idWarningMessage.text = "중복된 이메일 입니다."
                signupBinding.toSecondSignupButton.isEnabled = false
                signupBinding.sendVerifyButton.isEnabled = true
            }
        }



        // 아이디 이메일 형식 정규식 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != "") {
                    signupBinding.idTextFieldClear.visibility = VISIBLE
                    if(ValidationCheckUtil.isEmailValid(s.toString())){
                        signupBinding.idWarningMessage.visibility = INVISIBLE
                        signupBinding.sendVerifyButton.isEnabled = true
                        signupBinding.toSecondSignupButton.isEnabled = false
                    }else{
                        signupBinding.idWarningMessage.visibility = VISIBLE
                        signupBinding.idWarningMessage.text = "이메일 형식을 확인해주세요."
                        signupBinding.sendVerifyButton.isEnabled = false
                        signupBinding.toSecondSignupButton.isEnabled = false
                    }
                }
            }
        })

        // 이메일 중복 확인 검사 버튼 클릭 이벤트
        signupBinding.sendVerifyButton.setOnClickListener {
            if(signupBinding.sendVerifyButton.text == "변경"){
                signupBinding.idTextField.isEnabled = true
                signupBinding.sendVerifyButton.text = "인증"

                signupBinding.toSecondSignupButton.isEnabled = false
                signupBinding.idTextFieldClear.visibility = VISIBLE

            }else if(signupBinding.sendVerifyButton.text == "인증"){
                signUpViewModel.emailVerification(editTextID.text.toString())

                // idTextField 사용 불가 처리 & 인증 되신 변경으로 바꿈
                signupBinding.idTextField.isEnabled = false
                signupBinding.sendVerifyButton.text = "변경"

                signupBinding.idTextFieldClear.visibility = INVISIBLE
            }
        }

        //회원가입 두번째 화면(프래그먼트) 추가
        signupBinding.toSecondSignupButton.setOnClickListener{
            signupBinding.signupMainLayout.visibility = INVISIBLE

            signupBinding.signupMainLayout.isClickable = false
            signupBinding.signupMainLayout.isFocusable = false
            val editTextId = signupBinding.idTextField.text.toString()
            val bundle = Bundle().apply {
                putString("idText",editTextId)
            }
            replaceFragment(2, bundle)
        }

        // ID 입력 지우기 버튼 클릭
        signupBinding.idTextFieldClear.setOnClickListener {
            signupBinding.idTextField.setText("")
            signupBinding.toSecondSignupButton.isEnabled = false
            signupBinding.sendVerifyButton.isEnabled = false
            signupBinding.idWarningMessage.visibility = INVISIBLE
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
        signupBinding.signupMainLayout.visibility = VISIBLE
        signupBinding.signupMainLayout.isClickable = true
        signupBinding.signupMainLayout.isFocusable = true
    }
    // 로그인 액티비티로 전환
    fun BacktoTermsActivity(){
//        val terms_intent = Intent(this, TermsActivity::class.java)
        if(!isFinishing) finish()
    }


}