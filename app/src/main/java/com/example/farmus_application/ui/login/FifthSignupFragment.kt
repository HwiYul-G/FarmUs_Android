package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentSignupFifthBinding



class SignupFifthFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupFifthBinding

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
        viewBinding = FragmentSignupFifthBinding.inflate(inflater, container, false)

        viewBinding.signupRoleToolbar.toolbarWithTitleBackButton.setOnClickListener{
            SignupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit();
            SignupActivity!!.supportFragmentManager.popBackStack();
        }

        val btnBorrow : Button = viewBinding.selectBorrowButton
        val btnLend : Button = viewBinding.selectLendButton

        btnBorrow.setOnClickListener{
            viewBinding.backToMainButton.isEnabled = true
        }
        btnLend.setOnClickListener{
            viewBinding.backToMainButton.isEnabled = true
        }

        // TODO:  클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.skipToLoginButton.setOnClickListener{
            SignupActivity!!.BacktoTermsActivity()
        }

        // TODO:  클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.backToMainButton.setOnClickListener {
            SignupActivity!!.BacktoTermsActivity()
        }

        return viewBinding.root
    }

}