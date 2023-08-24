package com.farmus.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farmus.farmus_application.databinding.FragmentTermsOneBinding



class TermsOneFragment: Fragment(){

    private lateinit var viewBinding : FragmentTermsOneBinding

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
        viewBinding = FragmentTermsOneBinding.inflate(inflater, container, false)

        // 클릭 시 프래그먼트 종료 후 메인 액티비티 다시 활성화
        viewBinding.temrsOneToolbar.toolbarDeleteButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            termsActivity!!.activateMainLayout()
        }

        //클릭 시 다음 프래그먼트로 스왑
        viewBinding.temrsOneToolbar.toolbarNextButton.setOnClickListener {
            termsActivity!!.replaceFragment(2)
        }

        return viewBinding.root
    }
}