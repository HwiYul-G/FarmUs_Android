package com.example.farmus_application.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeSearchBinding
import com.example.farmus_application.ui.MainActivity
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeSearchFragment : Fragment() {

    private lateinit var binding: FragmentHomeSearchBinding

    private lateinit var callback: OnBackPressedCallback


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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideBottomNavigation(true)

        //fragment 이동시 searchBar로 focus
        binding.searchBar.requestFocus()

        //수정해야됨
        binding.searchBar.setOnClickListener {
            search()
        }


        //chip 아이템 임의로 설정 (원래는 검색된 단어로 설정)
        val chipItems = mutableListOf<String>() //chip 에 들어갈 list

        chipItems.add("경기도")
        chipItems.add("강원도")
        chipItems.add("서울")
        chipItems.add("충청북도")
        chipItems.add("충청남도")
        chipItems.add("전라북도")
        chipItems.add("전라남도")
        chipItems.add("제주도")

        //chip 전체 삭제
        binding.btnDeleteAll.setOnClickListener {
            clearChip()
        }

        //chip 동적 추가
        if (chipItems.size > 0) {
            for (i in chipItems) {
                addChip(i)
            }
        } else {
            clearChip()
        }
    }

    private fun search() {
        val searchText = binding.searchBar.text.toString()

        if (searchText != "") {
            setFragmentResult(
                "searchTextRequestKey",
                bundleOf("searchTextBundleKey" to searchText)
            )
            (activity as MainActivity).changeFragment(SearchFragment.newInstance("", ""))
            addChip(searchText)
        }
    }
    //chip 추가하는 함수
    private fun addChip(searchText: String) {
        val chip = Chip(requireContext())
        val radius: Float = 7.0f

        chip.text = searchText
        chip.closeIcon = getDrawable(requireContext(), R.drawable.cancel_vector_image)
        chip.chipStrokeColor = getColorStateList(requireContext(), R.color.gray_1)
        chip.chipCornerRadius = radius
        chip.chipStrokeWidth = 0.5f
        chip.chipBackgroundColor = getColorStateList(requireContext(), R.color.white)
        chip.isCloseIconVisible = true

        //삭제 버튼 누르면 chip 삭제
        chip.setOnCloseIconClickListener {
            binding.recentSearchChipgroup.removeView(chip)
        }
        //chip 버튼 클릭 이벤트
        chip.setOnClickListener {
            val chipText = chip.text.toString()

            setFragmentResult("selectTextRequestKey", bundleOf("bundleKey" to chipText))
            //SearchFragment로 이동
            (activity as MainActivity).changeFragment(SearchFragment.newInstance("", ""))
        }

        binding.recentSearchChipgroup.addView(chip)
    }

    private fun clearChip() {
        binding.recentSearchChipgroup.removeAllViews()

    }

    //뒤로가기 누르면 HomeSearchFragment로 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(HomeFragment.newInstance("", ""))
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    //뒤로가기 누르면 HomeFragment로 이동
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
         * @return A new instance of fragment HomeSearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}