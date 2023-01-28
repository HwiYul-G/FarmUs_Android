package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentFindIdResultBinding



class FindIdResultFragment: Fragment(){

    private lateinit var viewBinding : FragmentFindIdResultBinding

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
        viewBinding = FragmentFindIdResultBinding.inflate(inflater, container, false)

        // 이용자 이름 연동하여 표시
        val nameText = arguments?.getString("nameText")
        viewBinding.userName.setText("$nameText")

        // 이용자 아이디 연동하여 표시
        val idText = arguments?.getString("nameText")
        viewBinding.idResult.setText("$idText")

        return viewBinding.root
    }
}