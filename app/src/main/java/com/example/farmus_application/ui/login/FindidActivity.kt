package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindIdBinding
import com.example.farmus_application.ui.StartActivity
import com.example.farmus_application.viewmodel.findAccount.FindAccountViewModel

class FindidActivity : AppCompatActivity() {

    private lateinit var findIdBinding: ActivityFindIdBinding
    private val findAccountViewModel : FindAccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findIdBinding = ActivityFindIdBinding.inflate(layoutInflater)
        setContentView(findIdBinding.root)

        val editTextName : EditText = findIdBinding.nameTextField
        val editTextNum : EditText = findIdBinding.phoneNumberField

        // LiveData 객체에 observer 연결
//        findAccountViewModel.authCodeResponse.observe(this, {result->
//            // result는 SignUpVerificationRes 객체
//            // 이를 통해 서버로부터 받은 인증 관리 응답을 처리 ( 데이터의 변경사항을 UI에 반영하는 로직)
//            if(result.result){
//                // 인증번호 발송 성공시 UI 로직
//            }else{
//                // 인증 번호 발송 실패했을 때 UI 로직
//            }
//
//        })
//        findAccountViewModel.verificationResponse.observe(this, {result->
//            // result는 VerificationRes 객체
//            if(result.result){
//                // 아이디 찾기 성공시 UI 로직
//            }else{
//                // 아이디 찾기 실패시 UI 로직
//            }
////        })
//        findAccountViewModel.findAccountResponse.observe(this, {result->
//
//        })


        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        findIdBinding.findidFirstToolbar.toolbarMainTitleText.text = "아이디 찾기"

        // 추후 이전 화면에 따른 뒤로가기 연결 페이지 변화 필요
        // 뒤로가기
        findIdBinding.findidFirstToolbar.toolbarWithTitleBackButton.setOnClickListener(){
            val back_intent = Intent(this,StartActivity::class.java)
            if(!isFinishing) finish()
        }


        // 이름과 인증번호 입력 완료시에만 아이디 찾기 버튼 활성화/비활성화
        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                findIdBinding.findIdButton.isEnabled = false
                if((s!=null && s.toString() != "") && (editTextNum.text.toString()!=null&&editTextNum.text.toString()!="")){
                    findIdBinding.findIdButton.isEnabled = true
                }
            }
        })

        editTextNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                findIdBinding.findIdButton.isEnabled = false
                if((s!=null && s.toString() != "") && (editTextName.text.toString()!=null&&editTextName.text.toString()!="")){
                    findIdBinding.findIdButton.isEnabled = true
                }
            }
        })
        
        // clear 버튼(x버튼) 클릭시 name, phone number text field 클리어
        findIdBinding.nameTextFieldClear.setOnClickListener { editTextName.setText("") }
        findIdBinding.phoneNumberTextFieldClear.setOnClickListener { editTextNum.setText("") }

        // 아이디 찾기 화면(프래그먼트) 추가
        findIdBinding.findIdButton.setOnClickListener{
            findIdBinding.findidMainLayout.visibility = View.INVISIBLE
            findIdBinding.findidMainLayout.isClickable = false
            findIdBinding.findidMainLayout.isFocusable = false

            // TODO : 아이디 찾기
            // VM으로 받은 결과에는 result(true/false 여부)와 id가 들어있어야함.
            // result가 true이면 id 값을 bundle로 넘기고
            // result가 false이먄 id를 찾을 수 없다고 bundle로 넘기는 로직 처리 필요
            // findAccountViewModel.findAccount(editTextName.text.toString(), editTextNum.text.toString())

            val bundle = Bundle()
            bundle.putString("nameText", "${editTextName.text}")

            val findIdResultFragment = FindIdResultFragment()
            findIdResultFragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
                .replace(findIdBinding.findidFrameLayout.id, findIdResultFragment)
            transaction.commit()
        }

    }
    // 로그인 액티비티로 회귀
    fun BacktoLoginActivity(){
        val login_intent = Intent(this, LoginActivity::class.java)
        if(!isFinishing) finish()
    }
}