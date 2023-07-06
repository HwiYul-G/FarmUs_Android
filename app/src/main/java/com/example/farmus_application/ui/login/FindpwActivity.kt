package com.example.farmus_application.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.farmus_application.databinding.ActivityFindPwBinding
import androidx.activity.viewModels
import com.example.farmus_application.FindPwResultFragment
import com.example.farmus_application.viewmodel.login.FindPasswordViewModel
import java.util.regex.Pattern

class FindpwActivity : AppCompatActivity() {

    private lateinit var findPwBinding: ActivityFindPwBinding
    private val findPasswordViewModel : FindPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findPwBinding = ActivityFindPwBinding.inflate(layoutInflater)
        setContentView(findPwBinding.root)

        // 입력칸 관련 value 설정
        val editTextID : EditText = findPwBinding.emailTextField

        findPasswordViewModel.findPasswordResponse.observe(this){
            val bundle = Bundle()

            if (it.result){
                bundle.putBoolean("PW_Resent", true)
                bundle.putString("email", editTextID.text.toString())
            }else{
                bundle.putBoolean("PW_Resent", false)
                bundle.putString("email", editTextID.text.toString())
            }

            val findPwResultFragment = FindPwResultFragment()
            findPwResultFragment.arguments = bundle

            val transaction = supportFragmentManager.beginTransaction()
                .replace(findPwBinding.findpwFrameLayout.id, findPwResultFragment)
            transaction.commit()
        }

        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        findPwBinding.findpwFirstToolbar.toolbarMainTitleText.text = "비밀번호 찾기"

        findPwBinding.findpwFirstToolbar.toolbarWithTitleBackButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        editTextID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    val pattern: Pattern = Patterns.EMAIL_ADDRESS
                    if (!pattern.matcher(s).matches()) {
                        findPwBinding.idWarningMessage.visibility = View.VISIBLE
                        findPwBinding.sendTempPwButton.isEnabled = false
                    } else {
                        findPwBinding.idWarningMessage.visibility = View.INVISIBLE
                        findPwBinding.sendTempPwButton.isEnabled = false
                        findPwBinding.idCheckButton.isEnabled = true
                    }
                } else {
                    findPwBinding.idWarningMessage.visibility = View.INVISIBLE
                    findPwBinding.sendTempPwButton.isEnabled = false
                }
            }
        })

        findPwBinding.nameTextFieldClear.setOnClickListener {
            editTextID.setText("")
        }

        findPwBinding.idCheckButton.setOnClickListener {
            if(findPwBinding.idCheckButton.text == "확인"){
                findPwBinding.idCheckButton.text = "변경"
                findPwBinding.nameTextFieldClear.visibility = View.INVISIBLE
                findPwBinding.emailTextField.isEnabled = false
                findPwBinding.sendTempPwButton.isEnabled = true

            }else if(findPwBinding.idCheckButton.text == "변경"){
                findPwBinding.idCheckButton.text = "확인"
                findPwBinding.nameTextFieldClear.visibility = View.VISIBLE
                findPwBinding.emailTextField.isEnabled = true
                findPwBinding.sendTempPwButton.isEnabled = false
            }

        }

        findPwBinding.sendTempPwButton.setOnClickListener {
            findPwBinding.findpwMainLayout.visibility = View.INVISIBLE
            findPwBinding.findpwMainLayout.isClickable = false
            findPwBinding.findpwMainLayout.isFocusable = false

            findPasswordViewModel.findPassword(editTextID.text.toString())
        }

    }

    fun backtoLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        if(!isFinishing) finish()
    }
}