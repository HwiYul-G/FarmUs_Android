package com.example.farmus_application.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.BottomSheetFilterRegionBinding
import com.example.farmus_application.databinding.FragmentSearchBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.home.Adapter.FarmRVAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//검색바 누르면 나올 Fragment
class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var bottomSheetRegionBinding: BottomSheetFilterRegionBinding

    private var city = "전체"
    private var town = "전체"
    private lateinit var bottomSheetDialog: BottomSheetDialog

    private var adapter = FarmRVAdapter()

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
        setFragmentResultListener("searchTextRequestKey") { key, bundle ->
            searchText = bundle.getString("searchTextBundleKey")
            searchBinding.searchBar.setText(searchText)
        }
        //최근 검색어 선택한 경우
        setFragmentResultListener("selectTextRequestKey") { key, bundle ->
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
        searchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        bottomSheetRegionBinding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_filter_region, container, false)
        //bottomSheetDialog 설정
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        bottomSheetDialog.setContentView(bottomSheetRegionBinding.root)

        (activity as MainActivity).hideBottomNavigation(true)

        //toolbar 텍스트 없애기
        searchBinding.homeSearchTitleBar.toolbarMainTitleText.text = ""

        //bottomSheet 설정
        setBottomSheet()

        //필터 버튼 click 이벤트
        searchBinding.chipRegionFilter.setOnClickListener {
            clickChipRegionFilter()
        }
        //bottomSheetDialog 적용 버튼 클릭 이벤트
        bottomSheetRegionBinding.btnApply.setOnClickListener {
            clickBottomSheetApply()
        }

        //검색 결과 아이템
        val result_farm_items = mutableListOf<RVFarmDataModel>()

        //검색 결과 농장 리사이클러뷰
        searchBinding.rvHomeSearchFarm.adapter = adapter
        adapter.submitList(result_farm_items)
        searchBinding.rvHomeSearchFarm.layoutManager = GridLayoutManager(requireContext(), 2)
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )
        result_farm_items.add(
            RVFarmDataModel(
                R.drawable.farm_image_example,
                "고덕 주말 농장",
                "4.5평",
                "150,000"
            )
        )

        //툴바의 백버튼 누르면 HomeSearchFragment로 이동
        searchBinding.homeSearchTitleBar.toolbarWithTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("", ""))
        }

        return searchBinding.root
    }

    private fun clickBottomSheetApply() {
        city = bottomSheetRegionBinding.cityTextItem.text.toString()
        town = bottomSheetRegionBinding.townTextItem.text.toString()
        bottomSheetDialog.dismiss()
        searchBinding.chipRegionFilter.isChecked = true
        if (town == "전체") {
            searchBinding.chipRegionFilter.text = city
        } else {
            searchBinding.chipRegionFilter.text = "$city $town"
        }
    }

    private fun clickChipRegionFilter() {
        if (searchBinding.chipRegionFilter.isChecked) {
            bottomSheetDialog.show()
        } else {
            city = "전체"
            town = "전체"
            searchBinding.chipRegionFilter.text = "지역 필터링"
            bottomSheetRegionBinding.cityTextItem.setText(city)
            bottomSheetRegionBinding.townTextItem.setText(town)
            setBottomSheet()

        }
        searchBinding.chipRegionFilter.isChecked = bottomSheetRegionBinding.btnApply.isSelected
    }

    private fun setBottomSheet() {
        val cityItems = arrayListOf<String>()
        cityItems.addAll(resources.getStringArray(R.array.city_list))
        val cityItemAdapter =
            ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, cityItems)
        bottomSheetRegionBinding.cityTextItem.setAdapter(cityItemAdapter)

        val townItems = arrayListOf<String>()
        setDropdown(cityItems, townItems)
        val townItemAdapter =
            ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, townItems)
        bottomSheetRegionBinding.townTextItem.setAdapter(townItemAdapter)
    }

    //지역 선택 dropdowm
    private fun setDropdown(checkList: ArrayList<String>, itemList: ArrayList<String>) {
        bottomSheetRegionBinding.cityTextItem.setOnItemClickListener { adapterView, view, position, rowId ->
            itemList.clear()
            when (checkList[position]) {
                "서울특별시" -> itemList.addAll(resources.getStringArray(R.array.seoul_list))
                "경기도" -> itemList.addAll(resources.getStringArray(R.array.gyeonggi_list))
                "강원도" -> itemList.addAll(resources.getStringArray(R.array.gangwon_list))
                "충청북도" -> itemList.addAll(resources.getStringArray(R.array.chungbuk_list))
                "충청남도" -> itemList.addAll(resources.getStringArray(R.array.chungnam_list))
                "전라북도" -> itemList.addAll(resources.getStringArray(R.array.jeonbuk_list))
                "전라남도" -> itemList.addAll(resources.getStringArray(R.array.jeonnam_list))
                "경상북도" -> itemList.addAll(resources.getStringArray(R.array.gyeongbuk_list))
                "경상남도" -> itemList.addAll(resources.getStringArray(R.array.gyeongnam_list))
                "제주도" -> itemList.addAll(resources.getStringArray(R.array.jeju_list))
                "부산광역시" -> itemList.addAll(resources.getStringArray(R.array.busan_list))
                "대구광역시" -> itemList.addAll(resources.getStringArray(R.array.daegu_list))
                "인천광역시" -> itemList.addAll(resources.getStringArray(R.array.incheon_list))
                "광주광역시" -> itemList.addAll(resources.getStringArray(R.array.gwangju_list))
                "대전광역시" -> itemList.addAll(resources.getStringArray(R.array.daejeon_list))
                "울산광역시" -> itemList.addAll(resources.getStringArray(R.array.ulsan_list))
                "세종특별자치시" -> itemList.addAll(resources.getStringArray(R.array.sejong_list))
                else -> itemList.clear()
            }
        }
    }


    //뒤로가기 수행하면 화면 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("", ""))
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