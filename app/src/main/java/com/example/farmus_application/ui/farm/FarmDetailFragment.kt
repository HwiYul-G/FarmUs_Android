package com.example.farmus_application.ui.farm

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmDetailBinding
import com.example.farmus_application.model.farm.detail.DetailResult
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.viewmodel.farm.FarmDetailViewModel

class FarmDetailFragment: Fragment() {

    private lateinit var binding: FragmentFarmDetailBinding
    private lateinit var farmDetailViewModel: FarmDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_detail, container, false)
        farmDetailViewModel = ViewModelProvider(this)[FarmDetailViewModel::class.java]
        val farmImageAdapter = FarmImageAdapter()
        binding.farmDetailImage.adapter = farmImageAdapter
        val farmId = arguments?.getInt("farmId") ?: 0

        farmDetailViewModel.getFarmDetail(farmId)

        binding.farmDetailRequestCalendar.setOnClickListener {
            val bottomSheetDialog = CalendarBottomSheetDialog()
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }

        binding.farmDetailToolbar.toolbarWithoutTitleBackButton.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.back_vector_image_white)
            setOnClickListener { (activity as MainActivity).changeFragment(FarmFragment()) }
        }

        farmDetailViewModel.farmDetail.observe(viewLifecycleOwner) { detailRes ->
            val result = detailRes.result
            binding.farmDetail = result
            farmImageAdapter.setData(result)
            Log.e("FarmDetailResult","$result")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}