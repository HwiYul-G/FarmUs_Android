package com.example.farmus_application.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.home.Adapter.FarmRVAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private lateinit var adapter: FarmRVAdapter

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity as MainActivity).hideBottomNavigation(false)

        //검색바 누르면 HomeSearchFragment로 이동
        homeBinding.searchBar.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("", ""))
        }
        //파머스 추천 농장으로 이동
        homeBinding.btnFarmUsRecommendFarm.setOnClickListener {
            (activity as MainActivity).changeFragment(FarmusRecFarmFragment.newInstance("", ""))
        }

        adapter = FarmRVAdapter()

//      우리 동네 농장 아이템
        val local_farm_items = mutableListOf<RVFarmDataModel>()
        homeBinding.rvHomeFarm.adapter = adapter
        adapter.submitList(local_farm_items)
        homeBinding.rvHomeFarm.layoutManager = GridLayoutManager(requireActivity(), 2)
        local_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "3평",
                "150,000"
            )
        )
        local_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "3평",
                "150,000"
            )
        )
        local_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "3평",
                "150,000"
            )
        )
        local_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "3평",
                "150,000"
            )
        )

        return homeBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}