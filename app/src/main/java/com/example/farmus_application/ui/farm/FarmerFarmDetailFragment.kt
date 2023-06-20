package com.example.farmus_application.ui.farm

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
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmerFarmDetailBinding
import com.example.farmus_application.ui.MainActivity

class FarmerFarmDetailFragment : Fragment() {

    private lateinit var binding: FragmentFarmerFarmDetailBinding

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation(true)

        binding.farmerFarmDetailToolbar.toolbarWithoutTitleBackButton.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.back_vector_image_white)
            setOnClickListener { (activity as MainActivity).changeFragment(FarmFragment()) }
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
                        Toast.makeText(requireContext(), "글 수정 클릭", Toast.LENGTH_SHORT).show()
                    }
                    R.id.delete_post -> {
                        Toast.makeText(requireContext(), "글 삭제 클릭", Toast.LENGTH_SHORT).show()
                    }
                }
                false
            }
            popUpMenu.show()
        }

        binding.farmerFarmDetailRequestCalendar.setOnClickListener {
            // 불가 날짜 bottomSheetFragment
            val bottomSheetDialog = UnavailableBottomSheetDialog()
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }

        binding.farmerFarmDetailRequestList.setOnClickListener {
            // 요청온 목록 bottomSheetFragment
            val bottomSheetDialog = RequestBottomSheetDialog()
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }

    }
}