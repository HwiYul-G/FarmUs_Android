package com.example.farmus_application.ui.login

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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

        // 이용자 이름 연동하여 표시
        val nameText = arguments?.getString("nameText")
        viewBinding.userName.text = nameText

        // 이용자 아이디 연동하여 표시
//        val IDText = arguments?.getString("IDText")
//        viewBinding.idResult.text = IDText

        // 연동되어 표시된 아이디 클립보드에 복사하기
        viewBinding.copyIdText.setOnClickListener{
            var clipboardManager = FindidActivity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val hex = viewBinding.idResult.text
            val clip : ClipData = ClipData.newPlainText("pick color", hex)
            clipboardManager.setPrimaryClip(clip)
            Toast.makeText(requireActivity(), "아이디가 복사되었습니다.", Toast.LENGTH_LONG).show()
        }

        // 클릭 시 프래그먼트를 메인 로그인 페이지로 스왑
        viewBinding.backToLoginButton.setOnClickListener {
            FindidActivity!!.BacktoLoginActivity()
        }

        return viewBinding.root
    }
}