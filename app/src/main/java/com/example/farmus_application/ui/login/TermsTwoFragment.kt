package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentTermsTwoBinding



class TermsTwoFragment: Fragment(){

    private lateinit var viewBinding : FragmentTermsTwoBinding
    lateinit var checkBox : CheckBox

    private var termsActivity: TermsActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        termsActivity = context as TermsActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTermsTwoBinding.inflate(inflater, container, false)

        // 클릭 시 프래그먼트 종료 후 메인 액티비티 다시 활성화
        viewBinding.temrsTwoToolbar.toolbarDeleteButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            termsActivity!!.activateMainLayout()
        }

        //클릭 시 다음 프래그먼트로 스왑
        viewBinding.temrsTwoToolbar.toolbarNextButton.setOnClickListener {
            termsActivity!!.replaceFragment(3)
        }

        return viewBinding.root
    }
}