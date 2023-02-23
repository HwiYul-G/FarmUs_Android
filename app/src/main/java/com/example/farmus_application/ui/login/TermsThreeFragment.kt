package com.example.farmus_application.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentTermsThreeBinding



class TermsThreeFragment: Fragment(){

    private lateinit var viewBinding : FragmentTermsThreeBinding
    lateinit var checkBox : CheckBox

    var TermsActivity: TermsActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        TermsActivity = context as TermsActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTermsThreeBinding.inflate(inflater, container, false)

        // 클릭 시 프래그먼트 종료 후 메인 액티비티 다시 활성화
        viewBinding.temrsThreeToolbar.toolbarDeleteButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            TermsActivity!!.activateMainLayout()
        }

        //클릭 시 다음 프래그먼트로 스왑
        viewBinding.temrsThreeToolbar.toolbarNextButton.setOnClickListener {
            TermsActivity!!.replaceFragment(4)
        }

        //클릭 시 이용약관 액티비티와 체크 연동
        checkBox = viewBinding.checkboxSecond

        viewBinding.checkboxSecond.setOnClickListener{
            Log.i("checked","-----------------------------------2 is checked")
            if(checkBox.isChecked){
                TermsActivity!!.rememberChecked(true,2)
            } else {
                TermsActivity!!.rememberChecked(false,2)
            }
        }

        return viewBinding.root
    }
}