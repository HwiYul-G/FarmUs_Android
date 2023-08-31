package com.farm.farmus_application.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentChatTab2Binding
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.ui.message.adapter.ChatTab2ApprovedRVAdapter
import com.farm.farmus_application.ui.message.adapter.ChatTab2RVAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChatTab2Fragment : Fragment() {

    private lateinit var binding: FragmentChatTab2Binding

    private lateinit var tab2Adapter: ChatTab2RVAdapter
    private lateinit var tab2ApprovedAdapter: ChatTab2ApprovedRVAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_tab2, container, false)
        val view = binding
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val waitItem = mutableListOf<RVTab2DataModel>()
        tab2Adapter = ChatTab2RVAdapter()
        // adatper 내부에 있는 요소들 클릭 이벤트( 어뎁터 안의 interface 명세도 수정 필요)
        tab2Adapter.setOnItemClick(object : ChatTab2RVAdapter.OnItemClickListener {
            override fun itemClick() {
                // 필요시 bundle 등 추가
                val farmerChatFragment = FarmerChatFragment()
                (activity as MainActivity).changeFragmentAddToBackStack(farmerChatFragment)
            }
        })

        tab2Adapter.submitList(waitItem)
        binding.rvChatWait.adapter = tab2Adapter
        binding.rvChatWait.layoutManager = LinearLayoutManager(requireContext())
        waitItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        waitItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        waitItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        waitItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        waitItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )

        val approveItem = mutableListOf<RVTab2DataModel>()
        tab2ApprovedAdapter = ChatTab2ApprovedRVAdapter()
        tab2ApprovedAdapter.submitList(approveItem)
        binding.rvChatApprove.adapter = tab2ApprovedAdapter
        binding.rvChatApprove.layoutManager = LinearLayoutManager(requireContext())
        approveItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        approveItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        approveItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        approveItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )
        approveItem.add(
            RVTab2DataModel(
                R.drawable.farm_image_example,
                "서울 유일 농장",
                "2023.02.03~2023.05.07"
            )
        )

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatTab2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}