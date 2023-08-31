package com.farm.farmus_application.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentChatBinding
import com.farm.farmus_application.ui.message.adapter.ChatViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChatFragment : Fragment() {

    private lateinit var chatFragmentBinding: FragmentChatBinding

    private var tabTitleArray = arrayListOf<String>(
        "분양 요청 목록",
        "요청 온 목록"
    )


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
        chatFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        val view = chatFragmentBinding

        //툴바 백버튼 없애기
        view.toolbar.toolbarWithTitleBackButton.isVisible = false
        //툴바 타이틀 텍스트 설정
        view.toolbar.toolbarMainTitleText.text = "알림"

        val viewPager = view.viewPager
        val tabLayout = view.tabLayout
        //viewPagerAdapter
        viewPager.adapter = ChatViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        return view.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}