package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentSignupSecondBinding



class SignupSecondFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupSecondBinding

    var SignupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SignupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupSecondBinding.inflate(inflater, container, false)

        // 뒤로가기 - 프래그먼트 교체
        viewBinding.signupIdToolbar.toolbarWithTitleBackButton.setOnClickListener{
            SignupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit();
            SignupActivity!!.supportFragmentManager.popBackStack();
            SignupActivity!!.activateMainLayout()
        }

        val idText = arguments?.getString("idText")
        viewBinding.idTextField.setText("$idText")

        // 입력칸 관련 value 설정
        val editTextPW : EditText = viewBinding.pwTextField
        val editTextPwCheck : EditText = viewBinding.pwCheckTextField

        // 비밀번호 일치 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextPwCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    if (s.toString() != editTextPW.text.toString()) {
                        viewBinding.pwCheckWarningMessage.visibility = View.VISIBLE
                        viewBinding.toThirdSignupButton.isEnabled = false
                    } else {
                        viewBinding.pwCheckWarningMessage.visibility = View.INVISIBLE
                        viewBinding.toThirdSignupButton.isEnabled = true
                    }
                } else {
                    viewBinding.pwCheckWarningMessage.visibility = View.INVISIBLE
                    viewBinding.toThirdSignupButton.isEnabled = false
                }
            }
        })

        //클릭 시 프래그먼트를 3번으로 스왑
        viewBinding.toThirdSignupButton.setOnClickListener {
            SignupActivity!!.replaceFragment(3)
        }

        return viewBinding.root
    }
}