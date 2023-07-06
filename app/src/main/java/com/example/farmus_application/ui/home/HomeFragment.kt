package com.example.farmus_application.ui.home

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentHomeBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.home.Adapter.FarmRVAdapter
import com.example.farmus_application.viewmodel.home.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: FarmRVAdapter

    private val farmViewModel : HomeViewModel by viewModels()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation(false)

        //검색바 누르면 HomeSearchFragment로 이동
        binding.searchBar.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("", ""))
        }
        //파머스 추천 농장으로 이동
        binding.btnFarmUsRecommendFarm.setOnClickListener {
            (activity as MainActivity).changeFragment(FarmusRecFarmFragment.newInstance("", ""))
        }

        val dp = 16
        val px = dpToPx(requireContext(), dp.toFloat())
        adapter = FarmRVAdapter()

        farmViewModel.getFarmList()

        binding.rvHomeFarm.adapter = adapter
        binding.rvHomeFarm.addItemDecoration(GridSpaceItemDecoration(2, px.toInt()))
        binding.rvHomeFarm.layoutManager = GridLayoutManager(requireActivity(), 2)

        farmViewModel.farmListResponse.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    private fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
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