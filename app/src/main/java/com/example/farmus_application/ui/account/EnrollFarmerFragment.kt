package com.example.farmus_application.ui.account

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentEnrollFarmerBinding
import com.example.farmus_application.ui.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EnrollFarmerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnrollFarmerFragment : Fragment() {

    private lateinit var enrollFarmerBinding: FragmentEnrollFarmerBinding

    //뒤로가기 기능 구현
    private lateinit var callback: OnBackPressedCallback

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        enrollFarmerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_enroll_farmer,container,false)
        val view = enrollFarmerBinding

        //BottomNavigationView 숨기기
        (activity as MainActivity).hideBottomNavigation(true)

        view.btnAgree.isSelected = view.checkboxAgreeAll.isChecked

        //모두 동의합니다 체크 박스 기능
        view.checkboxAgreeAll.setOnClickListener {
            if(view.checkboxAgreeAll.isChecked) {
                view.checkboxPrivacy.isChecked = true
                view.checkboxTOS.isChecked = true
                view.btnAgree.isSelected = true
            } else {
                view.checkboxPrivacy.isChecked = false
                view.checkboxTOS.isChecked = false
                view.btnAgree.isSelected = false
            }
        }
        //이용약관 체크박스 클릭 이벤트
        view.checkboxTOS.setOnClickListener {
            if(view.checkboxTOS.isChecked){
                if(view.checkboxPrivacy.isChecked) {
                    view.checkboxAgreeAll.isChecked = true
                    view.btnAgree.isSelected = true
                } else {
                    view.btnAgree.isSelected = false
                }
            } else {
                view.checkboxAgreeAll.isChecked = false
                view.btnAgree.isSelected = false
            }
        }
        //개인정보 처리방침 체크박스 클릭이벤트
        view.checkboxPrivacy.setOnClickListener {
            if(view.checkboxPrivacy.isChecked){
                if(view.checkboxTOS.isChecked) {
                    view.checkboxAgreeAll.isChecked = true
                    view.btnAgree.isSelected = true
                } else view.btnAgree.isSelected = false
            } else{
                view.checkboxAgreeAll.isChecked = false
                view.btnAgree.isSelected = false
            }
        }

        view.toolBar.toolbarWithoutTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(MyPageFragment.newInstance("",""))
        }

        return view.root
    }

    //뒤로가기 누르면 MyPageFragment로 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(MyPageFragment.newInstance("",""))
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    //뒤로가기 누르면 MyPageFragment로 이동
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnrollFarmerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EnrollFarmerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}