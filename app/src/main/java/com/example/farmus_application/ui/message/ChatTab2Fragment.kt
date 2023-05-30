package com.example.farmus_application.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentChatTab2Binding
import com.example.farmus_application.ui.message.adapter.ChatTab2ApprovedRVAdapter
import com.example.farmus_application.ui.message.adapter.ChatTab2RVAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatTab2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatTab2Fragment : Fragment() {

    private lateinit var binding: FragmentChatTab2Binding

    private lateinit var tab2Adapter : ChatTab2RVAdapter
    private lateinit var tab2ApprovedAdapter : ChatTab2ApprovedRVAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_tab2, container, false)
        val view = binding

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val waitItem = mutableListOf<RVTab2DataModel>()
        tab2Adapter = ChatTab2RVAdapter()
        tab2Adapter.submitList(waitItem)
        binding.rvChatWait.adapter = tab2Adapter
        binding.rvChatWait.layoutManager = LinearLayoutManager(requireContext())
        waitItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        waitItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        waitItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        waitItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        waitItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))

        val approveItem = mutableListOf<RVTab2DataModel>()
        tab2ApprovedAdapter = ChatTab2ApprovedRVAdapter()
        tab2ApprovedAdapter.submitList(approveItem)
        binding.rvChatApprove.adapter = tab2ApprovedAdapter
        binding.rvChatApprove.layoutManager = LinearLayoutManager(requireContext())
        approveItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        approveItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        approveItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        approveItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        approveItem.add(RVTab2DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatTab2Fragment.
         */
        // TODO: Rename and change types and number of parameters
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