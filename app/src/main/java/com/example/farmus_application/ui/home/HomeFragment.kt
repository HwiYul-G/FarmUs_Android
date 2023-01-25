package com.example.farmus_application.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.favorite.ARG_PARAM1
import com.example.farmus_application.ui.favorite.ARG_PARAM2
import com.example.farmus_application.ui.favorite.FavoriteFragment
import com.example.farmus_application.ui.home.Adapter.LocalFarmRVAdapter
import com.example.farmus_application.ui.home.Adapter.RecRVAdapter

class HomeFragment : Fragment() {

    private lateinit var homeBinding : FragmentHomeBinding

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
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)

//      우리 동네 농장 아이템
        val local_farm_items = mutableListOf<RVFarmDataModel>()

        homeBinding.rvHomeFarm.adapter = LocalFarmRVAdapter(local_farm_items)
        homeBinding.rvHomeFarm.layoutManager = GridLayoutManager(requireActivity(), 2)

        // 농부를 위한 추천 콘텐츠 아이템
        val rec_contents_items = mutableListOf<RVRecDataModel>()

        homeBinding.rvRecContents.adapter = RecRVAdapter(rec_contents_items)
        homeBinding.rvRecContents.layoutManager = LinearLayoutManager(requireActivity())


        //searchBar 누르면 SearchFragment로 전환
        homeBinding.searchBar.setOnClickListener{
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(homeBinding.homeFragmentLayout.id, SearchFragment())
                .commitAllowingStateLoss()
        }

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
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}