package com.farm.farmus_application.ui.home

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentHomeBinding
import com.farm.farmus_application.repository.UserPrefsStorage
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.ui.farm.FarmDetailFragment
import com.farm.farmus_application.ui.home.Adapter.FarmRVAdapter
import com.farm.farmus_application.utilities.JWTUtils
import com.farm.farmus_application.viewmodel.home.HomeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: FarmRVAdapter

    private val homeViewModel : HomeViewModel by viewModels()

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

        val jwtToken = UserPrefsStorage.accessToken
        val email = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.email


        //검색바 누르면 HomeSearchFragment로 이동
        binding.searchBar.setOnClickListener {
            (activity as MainActivity).changeFragment(HomeSearchFragment.newInstance("", ""))
        }
        //파머스 추천 농장으로 이동
        binding.btnFarmUsRecommendFarm.setOnClickListener {
            // TODO : 23년 7월 20일 _ farmus 추천 농장 부분은 beta 버전으로 넘기면서 임시로 막음
            // (activity as MainActivity).changeFragment(FarmusRecFarmFragment.newInstance("", ""))
        }

        val dp = 16
        val px = dpToPx(requireContext(), dp.toFloat())
        adapter = FarmRVAdapter()
        homeViewModel.getFarmList(email.toString())

        adapter.setOnItemClick(object : FarmRVAdapter.OnItemClickListener {
            override fun itemClick(farmId: Int) {
                val farmDetailFragment = FarmDetailFragment()
                val bundle = Bundle().apply {
                    putInt("farmId", farmId)
                }
                farmDetailFragment.arguments = bundle
                (activity as MainActivity).changeFragmentAddToBackStack(farmDetailFragment)
            }

            override fun likeClick(email : String, farmId : Int) {
                homeViewModel.postLikeFarm(email, farmId)
            }

            override fun deleteLikeClick(email : String, farmId : Int) {
                homeViewModel.deleteLikeFarm(email, farmId)
            }
        })
        binding.rvHomeFarm.adapter = adapter
        binding.rvHomeFarm.addItemDecoration(GridSpaceItemDecoration(2, px.toInt()))
        binding.rvHomeFarm.layoutManager = GridLayoutManager(requireActivity(), 2)

        homeViewModel.farmListResponse.observe(viewLifecycleOwner) {
            // 매번 새롭게 리스트를 처리하기 위함
            adapter.submitList(null)
            adapter.submitList(it)
        }

        homeViewModel.isLikeFarmSuccess.observe(viewLifecycleOwner) {
            if(it == false){
                Toast.makeText(requireContext(), "찜하기가 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        homeViewModel.isDeleteLikeFarmSuccess.observe(viewLifecycleOwner){
            if(it == false){
                Toast.makeText(requireContext(), "찜하기 취소가 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
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