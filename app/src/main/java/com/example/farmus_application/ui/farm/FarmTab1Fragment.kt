package com.example.farmus_application.ui.farm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmTab1Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FarmTab1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FarmTab1Fragment : Fragment() {

    private lateinit var binding: FragmentFarmTab1Binding
    private lateinit var adapter : GetFarmRVAdapter

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
        binding = FragmentFarmTab1Binding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GetFarmRVAdapter()

        //최근 리사이클러뷰
        val rvRecentItems = mutableListOf<FarmDataModel>()
        adapter.submitList(rvRecentItems)
        binding.rvRecent.adapter = adapter
        binding.rvRecent.layoutManager = LinearLayoutManager(requireContext())

        rvRecentItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvRecentItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvRecentItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvRecentItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))


        //옛날 리사이클러뷰
        val rvPastItems = mutableListOf<FarmDataModel>()
        adapter.submitList(rvPastItems)
        binding.rvPast.adapter = adapter
        binding.rvPast.layoutManager = LinearLayoutManager(requireContext())
        rvPastItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvPastItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvPastItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))
        rvPastItems.add(FarmDataModel(R.drawable.farm_image_example, "고덕 농장 1구획","2022.12.25.","2023.01.07"))

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FarmTab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}