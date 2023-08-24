package com.farmus.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farmus.farmus_application.databinding.FragmentSignupFifthBinding
import com.farmus.farmus_application.model.user.signup.SignUpReq
import com.farmus.farmus_application.viewmodel.login.SignUpViewModel


class SignupFifthFragment: Fragment(){

    private lateinit var viewBinding : FragmentSignupFifthBinding
    private lateinit var signUpViewModel : SignUpViewModel

    private var signupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupFifthBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(requireActivity())[SignUpViewModel::class.java]

        viewBinding.signupRoleToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            signupActivity!!.supportFragmentManager.beginTransaction().remove(this).commit()
            signupActivity!!.supportFragmentManager.popBackStack()
        }

        signUpViewModel.isUserSignUpSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // 회원가입 성공
                signupActivity!!.BacktoTermsActivity()
            } else {
                // 회원가입 실패
                Toast.makeText(requireContext(), "회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        val editTextId = arguments?.getString("idText")
        val editTextPw = arguments?.getString("pwText")
        val editTextPhone = arguments?.getString("phoneText")
        val editTextName = arguments?.getString("nameText")
        val editTextNick = arguments?.getString("nickNameText")
        var ownerRole = "None"

//        val btnBorrow = viewBinding.selectBorrowButton
//        val btnLend = viewBinding.selectLendButton

        viewBinding.selectBorrowButton.setOnClickListener{
            viewBinding.backToMainButton.isEnabled = true
            ownerRole = "C"
        }
        viewBinding.selectLendButton.setOnClickListener{
            viewBinding.backToMainButton.isEnabled = true
            ownerRole = "F"
        }

        // TODO:  클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.skipToLoginButton.setOnClickListener{

            signupActivity!!.BacktoTermsActivity()
        }

        // TODO:  클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.backToMainButton.setOnClickListener {
            val signUpReq = SignUpReq(
                email = editTextId ?: "",
                name = editTextName ?: "",
                nickName = editTextNick ?: "",
                password = editTextPw ?: "",
                phoneNumber = editTextPhone ?: "",
                role = ownerRole
            )
            signUpViewModel.userSignUp(signUpReq)
        }

        return viewBinding.root
    }

}