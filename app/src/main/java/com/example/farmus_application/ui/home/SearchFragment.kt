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
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeBinding
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

    //뒤로 가기 기능
    private lateinit var callback: OnBackPressedCallback

    private var param1: String? = null
    private var param2: String? = null

    private var searchText: String? = null //검색창 텍스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //검색어 입력한 경우
        setFragmentResultListener("searchTextRequestKey"){key, bundle ->
            searchText = bundle.getString("searchTextBundleKey")
            searchBinding.searchBar.setText(searchText)
        }
        //최근 검색어 선택한 경우
        setFragmentResultListener("selectTextRequestKey"){ key, bundle ->
            searchText = bundle.getString("bundleKey")
            searchBinding.searchBar.setText(searchText)
        }

//        todo homeFilterFragment에서 적용 버튼 누르면 데이터 전달받음
//        setFragmentResultListener("FilterDataRequestKey") { key, bundle ->
//
//
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container, false)
        val view = searchBinding
        //toolbar 텍스트 없애기
        searchBinding.homeSearchTitleBar.toolbarMainTitleText.text = ""


//todo  수정해야됨
        //filter dropdown
        val filterAutoCompleteTextView = searchBinding.filterTextItem
        val filterItems = arrayListOf<String>("인기순", "최신순","조회순","찜 많은순")
        val filterItemAdapter = ArrayAdapter(requireContext(),R.layout.loc_dropdown_item_list,filterItems)
        filterAutoCompleteTextView.setAdapter(filterItemAdapter)

        //Spinner로 dropdown 하는 경우
//        val filterItems = arrayListOf<String>("인기순", "최신순","조회순","찜 많은순")
//        val adapter = ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, filterItems)
//        searchBinding.filterDropdown.adapter=adapter



        //option 버튼 클릭시 FilterFragment로 이동
        searchBinding.btnOption.setOnClickListener{
            (activity as MainActivity).changeFragment(HomeFilterFragment.newInstance("",""))
        }

        //툴바의 백버튼 누르면 HomeSearchFragment로 이동
        searchBinding.homeSearchTitleBar.toolbarWithTitleBackButton.setOnClickListener{
            (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("",""))
        }

        //검색 결과 아이템
        val result_farm_items = mutableListOf<RVFarmDataModel>()

        //검색 결과 농장 리사이클러뷰
        searchBinding.rvHomeSearchFarm.adapter = ResultRVAdapter(result_farm_items)
        searchBinding.rvHomeSearchFarm.layoutManager = GridLayoutManager(requireContext(), 2)
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))
        result_farm_items.add(RVFarmDataModel(R.drawable.farm_image_example, "고덕 주말 농장","4.5평","150,000"))


        return view.root
    }

    //뒤로가기 수행하면 화면 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("",""))
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

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