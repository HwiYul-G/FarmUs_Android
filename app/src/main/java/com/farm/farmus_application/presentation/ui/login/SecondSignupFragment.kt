package com.farm.farmus_application.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farm.farmus_application.utils.ValidationCheckUtil
import com.farm.farmus_application.databinding.FragmentSignupSecondBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignupSecondFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupSecondBinding
    private var signupActivity: SignupActivity? = null
    private var pwcCheck: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupSecondBinding.inflate(inflater, container, false)

        // 뒤로가기 - 프래그먼트 교체
        viewBinding.signupIdToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            signupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit()
            signupActivity!!.supportFragmentManager.popBackStack()
            signupActivity!!.activateMainLayout()
        }

        val editTextId = arguments?.getString("idText").toString()
        viewBinding.idTextField.setText(editTextId)

        // 입력칸 관련 value 설정
        val editTextPw = viewBinding.pwTextField
        val editTextPwCheck = viewBinding.pwCheckTextField

        // 비밀번호 자릿수에 따른 제한
        editTextPw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.toString() != "") {
                    if (ValidationCheckUtil.checkPassword(s.toString())) {
                        viewBinding.pwErrorText.visibility = View.INVISIBLE
                        pwcCheck = true
                    } else {
                        viewBinding.pwErrorText.visibility = View.VISIBLE
                        pwcCheck = false
                    }
                } else {
                    viewBinding.pwErrorText.visibility = View.INVISIBLE
                }
            }
        })

        // 비밀번호 일치 확인 후 주의 메세지 여부 및 다음 버튼 활성화 여부
        editTextPwCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if(s!=null && s.toString() != ""){
                    if (s.toString() != editTextPw.text.toString()) {
                        viewBinding.pwCheckWarningMessage.visibility = View.VISIBLE
                        viewBinding.toThirdSignupButton.isEnabled = false
                    } else {
                        viewBinding.pwCheckWarningMessage.visibility = View.INVISIBLE
                        if (pwcCheck) {
                            viewBinding.toThirdSignupButton.isEnabled = true
                        }
                    }
                } else {
                    viewBinding.pwCheckWarningMessage.visibility = View.INVISIBLE
                    viewBinding.toThirdSignupButton.isEnabled = false
                }
            }
        })

        //클릭 시 프래그먼트를 3번으로 스왑
        viewBinding.toThirdSignupButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("idText",editTextId)
                putString("pwText",editTextPw.text.toString())
            }
            signupActivity!!.replaceFragment(3,bundle)
        }

        return viewBinding.root
    }


}