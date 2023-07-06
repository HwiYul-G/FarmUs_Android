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
import com.example.farmus_application.viewmodel.login.FindAccountViewModel

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
        findAccountViewModel.findAccountRes.observe(this) {findAccountRes->
            val bundle = Bundle()
            bundle.putBoolean("result", findAccountRes.result)
            if(findAccountRes.result){
                bundle.putString("name", "${editTextName.text}")
                bundle.putString("email", findAccountRes.email)

            }else{
                bundle.putInt("code", findAccountRes.code)
                bundle.putString("nameText", "${editTextName.text}")

            }

            val findIdResultFragment = FindIdResultFragment()
            findIdResultFragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
                .replace(findIdBinding.findidFrameLayout.id, findIdResultFragment)
            transaction.commit()

        }


        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        findIdBinding.findidFirstToolbar.toolbarMainTitleText.text = "아이디 찾기"

        // 추후 이전 화면에 따른 뒤로가기 연결 페이지 변화 필요
        // 뒤로가기
        findIdBinding.findidFirstToolbar.toolbarWithTitleBackButton.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        // 이름과 인증번호 입력 완료시에만 아이디 찾기 버튼 활성화/비활성화
        editTextName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                findIdBinding.findIdButton.isEnabled = false
                if((s!=null && s.toString() != "") && (editTextNum.text.toString()!="")){
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
                if((s!=null && s.toString() != "") && (editTextName.text.toString()!="")){
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


            // 아래의 코드를 수행하면 livedata로 observe
            findAccountViewModel.findAccount(editTextName.text.toString(), editTextNum.text.toString())

        }

    }
    // 로그인 액티비티로 회귀
    fun backtoLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        if(!isFinishing) finish()
    }
}