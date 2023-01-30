package com.example.farmus_application.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeFilterBinding
import com.example.farmus_application.databinding.FragmentSearchBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.SplashActivity
import com.example.farmus_application.ui.home.Adapter.ResultRVAdapter
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//검색바 누르면 나올 Fragment
class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding

    private var param1: String? = null
    private var param2: String? = null
    private var chipText : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            chipText = it.getString("text")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container, false)
        val view = searchBinding

        //HomeSearchActivity 에서 선택한 chip의 text 받아오기
        view.searchBar.setText(chipText)

        //dropdown 오류 수정해야됨
        //dropdown
//        val filter_dropdown_item = resources.getStringArray(R.array.filter_list)
//        searchBinding.filterDropdown.adapter = ArrayAdapter(requireContext(), R.layout.fragment_search ,filter_dropdown_item)
//
//        searchBinding.filterDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//
//        }

        //option 버튼 클릭시 FilterFragment로 이동
        searchBinding.btnOption.setOnClickListener{
            (activity as HomeSearchActivity).changeFrame(HomeFilterFragment.newInstance("",""))
        }

        //툴바의 백버튼 누르면 HomeSearchActivity로 이동
        searchBinding.toolbarWithoutTitleBackButton.setOnClickListener{
            (activity as HomeSearchActivity).changeFragmentToActivity(HomeSearchActivity())
        }

        //검색 결과 아이템
        val result_farm_items = mutableListOf<RVFarmDataModel>()

        //result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "주말 농장 1", "100", "100,000"))

        //검색 결과 농장 리사이클러뷰
        searchBinding.rvHomeSearchFarm.adapter = ResultRVAdapter(result_farm_items)
        searchBinding.rvHomeSearchFarm.layoutManager = GridLayoutManager(requireContext(), 2)



        return view.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}