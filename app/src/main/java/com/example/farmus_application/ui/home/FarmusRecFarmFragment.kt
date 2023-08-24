package com.example.farmus_application.ui.home

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmusRecFarmBinding
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.home.Adapter.FarmRVAdapter

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FarmusRecFarmFragment : Fragment() {

    private lateinit var binding: FragmentFarmusRecFarmBinding

    private lateinit var adapter: FarmRVAdapter


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
            DataBindingUtil.inflate(inflater, R.layout.fragment_farmus_rec_farm, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideBottomNavigation(true)

        //툴바 설정
        binding.toolBar.toolbarMainTitleText.text = "파머스 추천 농장"
        binding.toolBar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        //툴바 백버튼 누르면 홈
        binding.toolBar.toolbarWithTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeFragment.newInstance("", ""))
        }

        val dp = 16
        val px = dpToPx(requireContext(), dp.toFloat())

        // TODO : 날라오는 데이터의 값에 따라 어뎁터 변경 필요
        adapter = FarmRVAdapter()

        binding.rvFarm.adapter = adapter
        binding.rvFarm.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFarm.addItemDecoration(GridSpaceItemDecoration(2, px.toInt()))

        // TODO : viewModel에서 observe로 데이터 관찰후 받아오는 것 작성
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
         * @return A new instance of fragment FarmusRecFarmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FarmusRecFarmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}