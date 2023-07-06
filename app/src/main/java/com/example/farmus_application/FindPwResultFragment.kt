package com.example.farmus_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.farmus_application.databinding.FragmentFindPwResultBinding
import com.example.farmus_application.ui.login.FindpwActivity


class FindPwResultFragment : Fragment() {

    private lateinit var viewBinding : FragmentFindPwResultBinding

    private val findpwActivity: FindpwActivity by lazy { activity as FindpwActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFindPwResultBinding.inflate(inflater, container, false)
        val result = arguments?.getBoolean("PW_Resent")

        val email = arguments?.getString("email")
        if(result!!){
            viewBinding.email.text = email
        }else{
            viewBinding.textview1.text = "비밀번호 재설정 실패"
            viewBinding.email.text = email+"로 가입된 계정이 없습니다."
            viewBinding.textView2.text = "이메일을 다시 확인해주세요."
        }

        viewBinding.backToLoginButton.setOnClickListener {
            findpwActivity.onBackPressed()
        }

        return viewBinding.root
    }


}