package com.example.farmus_application.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentChatTab1Binding
import com.example.farmus_application.ui.message.Adapter.ChatTab1RVAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatTab1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatTab1Fragment : Fragment() {

    private lateinit var chatTab1FragmentBinding : FragmentChatTab1Binding

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
        chatTab1FragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_tab1, container, false)
        val view = chatTab1FragmentBinding

        val requestItem = mutableListOf<RVTab1DataModel>()
        view.rvChatTab1.adapter = ChatTab1RVAdapter(requestItem)
        view.rvChatTab1.layoutManager = LinearLayoutManager(requireContext())
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))
        requestItem.add(RVTab1DataModel(R.drawable.farm_image_example, "서울 유일 농장", "2023.02.03~2023.05.07"))



        return view.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatTab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
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