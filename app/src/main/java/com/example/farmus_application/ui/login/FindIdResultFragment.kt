package com.example.farmus_application.ui.login

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentFindIdResultBinding



class FindIdResultFragment: Fragment(){

    private lateinit var viewBinding : FragmentFindIdResultBinding

    var FindidActivity: FindidActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FindidActivity = context as FindidActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFindIdResultBinding.inflate(inflater, container, false)

        // 추후 이전 화면에 따른 메인툴바의 설명 표시 변화 필요
        viewBinding.findidFirstToolbar.toolbarMainTitleText.text = "아이디 찾기"

        val code = arguments?.getInt("code", -1) // 기본값으로 -1 설정
        when (code) {
            0 -> {
                val name = arguments?.getString("name")
                val email = arguments?.getString("email")
                viewBinding.userName.text = name
                viewBinding.idResult.text = email
            }
            6004 -> {
                val nameText = arguments?.getString("nameText")
                val errorMessage = arguments?.getString("email")
                viewBinding.userName.text = nameText
                viewBinding.idExplainText.text = "님 죄송합니다."
                viewBinding.idResult.visibility =  View.GONE
                viewBinding.idExplainText2.text = errorMessage
                viewBinding.copyIdText.visibility = View.GONE

            }
            else -> {
                val nameText = arguments?.getString("nameText")
                viewBinding.idExplainText.text = "님 죄송합니다."
                viewBinding.idResult.visibility =  View.GONE
                viewBinding.idExplainText2.text = "알 수 없는 문제가 발생했습니다."
                viewBinding.copyIdText.visibility = View.GONE
            }
        }

        // 연동되어 표시된 아이디 클립보드에 복사하기
        viewBinding.copyIdText.setOnClickListener{
            var clipboardManager = FindidActivity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val hex = viewBinding.idResult.text
            val clip : ClipData = ClipData.newPlainText("pick color", hex)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(requireActivity(), "아이디가 복사되었습니다.", Toast.LENGTH_LONG).show()
        }

        viewBinding.findidFirstToolbar.toolbarWithTitleBackButton.setOnClickListener {
            FindidActivity!!.BacktoLoginActivity()
        }

        // 클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.backToLoginButton.setOnClickListener {
            FindidActivity!!.BacktoLoginActivity()
        }

        return viewBinding.root
    }
}