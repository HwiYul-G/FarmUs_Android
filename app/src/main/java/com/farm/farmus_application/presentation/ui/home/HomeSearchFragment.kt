package com.farm.farmus_application.presentation.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentHomeSearchBinding
import com.farm.farmus_application.UserPrefsStorage
import com.farm.farmus_application.ui.MainActivity
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

        // 검색바 클릭 이벤트
        binding.searchBar.setOnClickListener {
            search()
        }


        // TODO : homeSearchHistory를 자동으로 updated하는 부분
        binding.recentSearchChipgroup.removeAllViews()
        UserPrefsStorage.recentSearches.forEach { keyword->
            addChip(keyword)
        }

        //chip 전체 삭제
        binding.btnDeleteAll.setOnClickListener {
            clearChip()
        }


        //툴바의 백버튼 누르면 HomeFragment로 이동
        binding.homeSearchTitleBar.toolbarWithoutTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeFragment())
        }
    }

    private fun search() {
        val searchText = binding.searchBar.text.toString()

        if (searchText != "") {
            setFragmentResult(
                "searchTextRequestKey",
                bundleOf("searchTextBundleKey" to searchText)
            )
            (activity as MainActivity).changeFragment(SearchFragment())
            // VM을 통한 DB에 저장
            UserPrefsStorage.addRecentSearch(searchText)
        }
    }

    //chip 추가하는 함수
    private fun addChip(searchText: String) {
        val radius: Float = 7.0f
        val chip = Chip(requireContext()).apply {
            text = searchText
            closeIcon = getDrawable(requireContext(), R.drawable.cancel_vector_image)
            chipStrokeColor = getColorStateList(requireContext(), R.color.gray_1)
            chipCornerRadius = radius
            chipStrokeWidth = 0.5f
            chipBackgroundColor = getColorStateList(requireContext(), R.color.white)
            isCloseIconVisible = true
        }


        //삭제 버튼 누르면 chip 삭제
        chip.setOnCloseIconClickListener {
            binding.recentSearchChipgroup.removeView(chip)
            UserPrefsStorage.removeRecentSearch(searchText)
        }
        //chip 버튼 클릭 이벤트
        chip.setOnClickListener {
            val chipText = chip.text.toString()
            setFragmentResult("selectTextRequestKey", bundleOf("bundleKey" to chipText))
            //SearchFragment로 이동
            (activity as MainActivity).changeFragment(SearchFragment())
        }

        binding.recentSearchChipgroup.addView(chip)
    }


    private fun clearChip() {
        binding.recentSearchChipgroup.removeAllViews()
        UserPrefsStorage.clearRecentSearches()
    }

    //뒤로가기 누르면 HomeSearchFragment로 이동
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).changeFragment(HomeFragment())
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