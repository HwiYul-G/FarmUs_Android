package com.example.farmus_application.ui.favorite

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFavoriteBinding
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.farm.FarmDetailFragment
import com.example.farmus_application.ui.home.Adapter.FavoriteRVAdapter
import com.example.farmus_application.ui.home.GridSpaceItemDecoration
import com.example.farmus_application.utilities.JWTUtils
import com.example.farmus_application.viewmodel.favorite.FavoriteViewModel


const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var adapter: FavoriteRVAdapter

    private val favoriteViewModel: FavoriteViewModel by viewModels()


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
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //툴바 설정
        binding.toolBar.toolbarMainTitleText.text = "좋아요"
        binding.toolBar.toolbarWithTitleBackButton.visibility = View.GONE
        binding.toolBar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        val jwtToken = UserPrefsStorage.accessToken
        val email = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.email


        val dp = 16
        val px = dpToPx(requireContext(), dp.toFloat())

        adapter = FavoriteRVAdapter()

        favoriteViewModel.getFavoriteFarmList(email.toString())

        // adapter 내부의 이벤트 관련
        adapter.setOnItemClick(object : FavoriteRVAdapter.OnItemClickListener {
            override fun itemClick(farmId: Int) {
                val farmDetailFragment = FarmDetailFragment()
                val bundle = Bundle().apply {
                    putInt("farmId", farmId)
                }
                farmDetailFragment.arguments = bundle
                (activity as MainActivity).changeFragmentAddToBackStack(farmDetailFragment)
            }

            override fun likeClick(email: String, farmId: Int) {
                favoriteViewModel.postLikeFarm(email, farmId)
            }

            override fun deleteLikeClick(email: String, farmId: Int) {
                favoriteViewModel.deleteLikeFarm(email, farmId)
            }

        })
        binding.rvFarm.adapter = adapter
        binding.rvFarm.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvFarm.addItemDecoration(GridSpaceItemDecoration(2, px.toInt()))


        favoriteViewModel.favoriteFarmResponse.observe(viewLifecycleOwner){
            adapter.submitList(null)
            if(it.result){
                // View visible 조정
                binding.rvFarm.visibility = View.VISIBLE
                binding.emptyDataParent.root.visibility = View.GONE
                // adapter에 데이터 전달
                adapter.submitList(it.farmList)
            }else{
                binding.rvFarm.visibility = View.GONE
                binding.emptyDataParent.root.visibility = View.VISIBLE
                binding.emptyDataParent.emptyItemTextview.text = "찜한 농장이 없습니다."
            }
        }

        favoriteViewModel.isLikeFarmSuccess.observe(viewLifecycleOwner) {
            if (it == false) {
                Toast.makeText(requireContext(), "찜하기가 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        favoriteViewModel.isDeleteLikeFarmSuccess.observe(viewLifecycleOwner) {
            if (it == false) {
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
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}