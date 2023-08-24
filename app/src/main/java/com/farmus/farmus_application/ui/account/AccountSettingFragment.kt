package com.farmus.farmus_application.ui.account

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.farmus.farmus_application.databinding.FragmentAccountSettingBinding
import com.farmus.farmus_application.ui.MainActivity
import com.farmus.farmus_application.ui.StartActivity
import com.farmus.farmus_application.viewmodel.account.AccountSettingViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountSettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountSettingFragment : Fragment() {

    private lateinit var binding : FragmentAccountSettingBinding
    //뒤로가기 기능 구현
    private lateinit var callback: OnBackPressedCallback
    private lateinit var viewModel: AccountSettingViewModel

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
        binding = FragmentAccountSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountSettingViewModel::class.java)

        binding.toolbar.toolbarMainTitleText.apply {
            text = "설정"
        }
        //툴바 백터튼 누르면 MyPageFragment로 이동
        binding.toolbar.toolbarWithTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(MyPageFragment.newInstance("",""))
        }

        //로그아웃 버튼
        binding.layerLogout.setOnClickListener{
            //Datastore(로그인 유저정보) 비우기
            viewModel.clearUserData()

            val intent = Intent(requireContext(), StartActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
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
         * @return A new instance of fragment AccountSettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountSettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}