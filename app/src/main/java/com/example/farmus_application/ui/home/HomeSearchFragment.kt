package com.example.farmus_application.ui.home

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
import androidx.lifecycle.ViewModelProvider
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeSearchBinding
import com.example.farmus_application.model.home.Entity.SearchedKeyword
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.viewmodel.home.HomeSearchViewModel
import com.google.android.material.chip.Chip

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeSearchFragment : Fragment() {

    private lateinit var binding: FragmentHomeSearchBinding
    private lateinit var callback: OnBackPressedCallback
    private lateinit var homeSearchViewModel: HomeSearchViewModel


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

        homeSearchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        )[HomeSearchViewModel::class.java]


        //fragment 이동시 searchBar로 focus
        binding.searchBar.requestFocus()

        // 검색바 클릭 이벤트
        binding.searchBar.setOnClickListener {
            search()
        }


        homeSearchViewModel.allSearchedKeyword.observe(viewLifecycleOwner) {keywordList->
            binding.recentSearchChipgroup.removeAllViews()
            keywordList.forEach { keyword->
                addChip(keyword.keyword)
            }
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
            val currentDate = homeSearchViewModel.getDate()
            homeSearchViewModel.insertSearchedKeyword(SearchedKeyword(searchText, currentDate))
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
            homeSearchViewModel.deleteSearchedKeyword(searchText)
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
        homeSearchViewModel.deleteAllSearchedKeyword()
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