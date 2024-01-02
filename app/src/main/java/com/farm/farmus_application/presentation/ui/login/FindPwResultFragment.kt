package com.farm.farmus_application.presentation.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farm.farmus_application.databinding.FragmentFindPwResultBinding


class FindPwResultFragment : Fragment() {

    private lateinit var viewBinding : FragmentFindPwResultBinding

    private lateinit var findpwActivity: FindpwActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FindpwActivity) {
            findpwActivity = context
        } else {
            throw IllegalArgumentException("Activity must be FindpwActivity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFindPwResultBinding.inflate(inflater, container, false)
        val result = arguments?.getBoolean("PW_Resent")
        val email = arguments?.getString("email")

        if(result!!){
            viewBinding.email.text = email
        }else{
            viewBinding.textview1.text = "비밀번호 재설정 실패"
            viewBinding.email.text = email
            viewBinding.textView2.text = "위의 이메일이 맞나요? 이메일을 다시 확인해주세요."
        }

        viewBinding.backToLoginButton.setOnClickListener {
            findpwActivity.backtoLoginActivity()
        }

        return viewBinding.root
    }


}