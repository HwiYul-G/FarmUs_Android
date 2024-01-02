package com.farm.farmus_application.presentation.ui.farm

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmDetailBinding
import com.farm.farmus_application.presentation.viewmodel.farm.FarmDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FarmDetailFragment: Fragment() {

    private lateinit var binding: FragmentFarmDetailBinding
    private val farmDetailViewModel: FarmDetailViewModel by viewModels()
    private lateinit var bottomSheetDialog: CalendarBottomSheetDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_detail, container, false)
        val farmImageAdapter = FarmImageAdapter()
        binding.farmDetailImage.adapter = farmImageAdapter
        val farmId = arguments?.getInt("farmId") ?: 0

        farmDetailViewModel.getFarmDetail(farmId)

        binding.farmDetailToolbar.toolbarWithoutTitleBackButton.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.back_vector_image_white)
            setOnClickListener {
                activity?.supportFragmentManager?.apply {
                    beginTransaction().remove(this@FarmDetailFragment).commit()
                    popBackStack()
                }
            }
        }

        farmDetailViewModel.farmDetail.observe(viewLifecycleOwner) { detailRes ->
            binding.farmDetail = detailRes
            farmImageAdapter.setData(detailRes)
            bottomSheetDialog = CalendarBottomSheetDialog(detailRes)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.farmDetailRequestApplication.setOnClickListener {
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }


    }

}