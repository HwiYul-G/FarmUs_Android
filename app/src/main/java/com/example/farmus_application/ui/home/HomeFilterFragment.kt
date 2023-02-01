package com.example.farmus_application.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeFilterBinding
import com.example.farmus_application.ui.MainActivity
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFilterFragment : Fragment() {

    private lateinit var homeFilterBinding : FragmentHomeFilterBinding

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
        homeFilterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_filter, container, false)
        val view = homeFilterBinding

        //BottomNavigationView 숨기기
        (activity as MainActivity).hideBottomNavigation(true)

//      상단바 텍스트 설정
        homeFilterBinding.filterToolbar.toolbarMainTitleText.text = "필터"

//todo  적용 버튼 클릭 이벤트
        homeFilterBinding.btnApplyData.setOnClickListener {
//            val loc_city = homeFilterBinding.cityTextItem.text.toString()
//            val loc_town = homeFilterBinding.townTextItem.text.toString()
//            val price_start = homeFilterBinding.priceRangeSliderBar.valueTo.toInt()
//            val price_end = homeFilterBinding.priceRangeSliderBar.valueFrom.toInt()
//            val area_selected_num = homeFilterBinding.areaChipgroup.checkedChipId.toString()
//            val date_start = homeFilterBinding.textStartDay.toString()
//            val date_end = homeFilterBinding.textEndDay.toString()
//
//            val dataModel = FilterDataModel(loc_city,loc_town,price_start,price_end,area_selected_num,date_start,date_end)
//
//            setFragmentResult("FilterDataRequestKey", bundleOf("loc_city" to loc_city))
//            setFragmentResult("FilterDataRequestKey", bundleOf("loc_town" to loc_town))
//            setFragmentResult("FilterDataRequestKey", bundleOf("price_start" to price_start))
//            setFragmentResult("FilterDataRequestKey", bundleOf("price_end" to price_end))
//            setFragmentResult("FilterDataRequestKey", bundleOf("area_selected_num" to area_selected_num))
//            setFragmentResult("FilterDataRequestKey", bundleOf("date_start" to date_start))
//            setFragmentResult("FilterDataRequestKey", bundleOf("date_end" to date_end))

            (activity as MainActivity).changeFragment(SearchFragment.newInstance("",""))

        }

        //지역 설정 시/도
        val cityAutoCompleteTextView = homeFilterBinding.cityTextItem

        val cityItems = arrayListOf<String>("전체", "서울특별시", "경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주특별자치도","부산광역시","대구광역시","인천광역시", "광주광역시","대전광역시","울산광역시","세종특별자치시")
        val cityItemAdapter = ArrayAdapter(requireContext(),R.layout.loc_dropdown_item_list,cityItems)
        cityAutoCompleteTextView.setAdapter(cityItemAdapter)

        //지역 설정 시/군/구
        val townAutoCompleteTextView = homeFilterBinding.townTextItem

        val townItems = arrayListOf<String>()
        setDropdown(cityItems, townItems, cityAutoCompleteTextView, townAutoCompleteTextView)

        val countryItemAdapter = ArrayAdapter(requireContext(), R.layout.loc_dropdown_item_list, townItems)
        townAutoCompleteTextView.setAdapter(countryItemAdapter)


        //툴바 back버튼 누르면 SearchFragment로 돌아가기
        homeFilterBinding.filterToolbar.toolbarWithTitleBackButton.setOnClickListener{
            (activity as MainActivity).changeFragment(SearchFragment.newInstance("",""))
        }

//      DatePickerDialog- 시작 날짜
        homeFilterBinding.startDay.setOnClickListener {

            showDatePickerDialog(homeFilterBinding.textStartDay)
        }

//      DatePickerDialog - 끝나는 날짜
        homeFilterBinding.endDay.setOnClickListener {

            showDatePickerDialog(homeFilterBinding.textEndDay)
        }

        return view.root
    }

    //fragment 에서 나가면 BottomNavigationView 다시 보이게
    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideBottomNavigation(false)
    }

    //지역 선택 dropdowm
    private fun setDropdown(checkList: ArrayList<String>, itemList: ArrayList<String>,cityAutoCompleteTextView: AutoCompleteTextView, townCompleteTextView: AutoCompleteTextView) {
        cityAutoCompleteTextView.setOnItemClickListener{ adapterView, view, position, rowId ->
            itemList.clear()
            when(checkList[position]){
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
                else -> itemList.add("전체")
            }
        }
    }

    //달력 Dialog 띄우기
    private fun showDatePickerDialog(date : TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
            // y년 (m+1)월 d일
            date.text = "${y}.${m + 1}.${d}."
        }

        //show를 통해 dialog창 활성화
        val picker = DatePickerDialog(requireContext(), listener, year, month, day)
        picker.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFilterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFilterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}