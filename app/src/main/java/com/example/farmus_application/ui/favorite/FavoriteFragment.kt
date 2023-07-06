package com.example.farmus_application.ui.favorite

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
import com.example.farmus_application.databinding.FragmentFavoriteBinding
import com.example.farmus_application.ui.home.Adapter.FavoriteRVAdapter
import com.example.farmus_application.ui.home.GridSpaceItemDecoration
import com.example.farmus_application.ui.home.RVFarmDataModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding

    private lateinit var adapter : FavoriteRVAdapter

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //툴바 설정
        binding.toolBar.toolbarMainTitleText.text = "좋아요"
        binding.toolBar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        val farmItems = mutableListOf<RVFarmDataModel>()

        val dp = 16
        val px = dpToPx(requireContext(), dp.toFloat())

        adapter = FavoriteRVAdapter()
        binding.rvFarm.adapter = adapter
        adapter.submitList(farmItems)
        binding.rvFarm.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvFarm.addItemDecoration(GridSpaceItemDecoration(2,px.toInt()))
        // RV 데이터 클래스 변경에 따라서 임시 데이터 형식도 수정
        farmItems.add(RVFarmDataModel("","고덕 주말 농장","3평","150,000"))
        farmItems.add(RVFarmDataModel("","고덕 주말 농장","3평","150,000"))
        farmItems.add(RVFarmDataModel("","고덕 주말 농장","3평","150,000"))
        farmItems.add(RVFarmDataModel("","고덕 주말 농장","3평","150,000"))


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
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}