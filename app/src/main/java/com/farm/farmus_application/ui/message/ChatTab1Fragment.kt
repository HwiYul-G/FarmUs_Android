package com.farm.farmus_application.ui.message

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentChatTab1Binding
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.ui.message.adapter.ChatTab1RVAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChatTab1Fragment : Fragment() {
    private val TAG = "ChatTab1FragmentTAG"

    private lateinit var binding: FragmentChatTab1Binding
    private lateinit var adapter: ChatTab1RVAdapter


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
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_tab1, container, false)
        val view = binding

        adapter = ChatTab1RVAdapter()
        // adatper 내부에 있는 요소들 클릭 이벤트 (어뎁터 안의 interface 명세도 변경 필요)
        adapter.setOnItemClick(object : ChatTab1RVAdapter.OnItemClickListener {
            override fun itemClick() {
                // 필요시 bundle 추가
                val clientChatFragment = ClientChatFragment()
                (activity as MainActivity).changeFragmentAddToBackStack(clientChatFragment)
            }
        })

        val requestItem = mutableListOf<RVTab1DataModel>()
        view.rvChatTab1.adapter = adapter
        adapter.submitList(requestItem)
        view.rvChatTab1.layoutManager = LinearLayoutManager(requireContext())
        requestItem.add(
            RVTab1DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        requestItem.add(
            RVTab1DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        requestItem.add(
            RVTab1DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        requestItem.add(
            RVTab1DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        requestItem.add(
            RVTab1DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )


        return view.root
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatTab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
