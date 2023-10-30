package com.farm.farmus_application.ui.farm

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmerFarmDetailBinding
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.viewmodel.farm.FarmDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FarmerFarmDetailFragment : Fragment() {

    private lateinit var binding: FragmentFarmerFarmDetailBinding
    private val farmDetailViewModel: FarmDetailViewModel by viewModels()
    private lateinit var farmImageAdapter: FarmImageAdapter
    private var farmId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_farmer_farm_detail,
            container,
            false
        )
        getFarmDetail()
        settingImageAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation(true)

        binding.farmerFarmDetailToolbar.toolbarWithoutTitleBackButton.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.back_vector_image_white)
            setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    beginTransaction().remove(this@FarmerFarmDetailFragment).commit()
                    popBackStack()
                }
            }
        }

        farmDetailViewModel.farmDetail.observe(viewLifecycleOwner) { detailResult ->
            binding.farmDetail = detailResult
            farmImageAdapter.setData(detailResult)
            farmId = detailResult.FarmID
        }

        binding.farmerFarmDetailEtcButton.setOnClickListener {
            val contextThemeWrapper = ContextThemeWrapper(requireContext(), R.style.MyPopupMenu)
            var popUpMenu = PopupMenu(
                contextThemeWrapper,
                binding.farmerFarmDetailEtcButton,
                Gravity.END,
                0,
                R.style.MyPopupMenu
            )
            requireActivity().menuInflater.inflate(R.menu.popup_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.modify_post -> {
                        //글 수정 클릭
                        farmDetailViewModel.saveTempFarmDetail(farmDetailViewModel.farmDetail.value) //글 수정 뷰로 데이터 넘기기
                        (activity as MainActivity).changeFragment(FarmModifyFragment())
                    }
                    R.id.delete_post -> {
                        //글 삭제 클릭
                        Toast.makeText(requireContext(), "글 삭제 클릭", Toast.LENGTH_SHORT).show()
                    }
                }
                false
            }
            popUpMenu.show()
        }

        binding.farmerFarmDetailRequestCalendar.setOnClickListener {
            // 불가 날짜 bottomSheetFragment
            val managementCalendarFragment = ManagementCalendarFragment()
            val bundle = Bundle().apply {
                putInt("farmId",farmId)
            }
            managementCalendarFragment.arguments = bundle
            (activity as MainActivity).changeFragmentAddToBackStack(managementCalendarFragment)

        }

//        binding.farmerFarmDetailRequestList.setOnClickListener {
//            // 요청온 목록 bottomSheetFragment
//            val bottomSheetDialog = RequestBottomSheetDialog()
//            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
//        }

    }

    private fun getFarmDetail() {
        val farmId = arguments?.getInt("farmId") ?: 0
        farmDetailViewModel.getFarmDetail(farmId)
    }

    private fun settingImageAdapter() {
        farmImageAdapter = FarmImageAdapter()
        binding.farmerFarmDetailImage.adapter = farmImageAdapter
    }

}