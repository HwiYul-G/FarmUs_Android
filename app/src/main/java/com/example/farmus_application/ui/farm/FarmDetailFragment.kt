package com.example.farmus_application.ui.farm

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmDetailBinding
import com.example.farmus_application.model.reserve.request.ReserveRequestReq
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.viewmodel.farm.FarmDetailViewModel
import com.google.gson.annotations.SerializedName
import com.prolificinteractive.materialcalendarview.CalendarDay

class FarmDetailFragment: Fragment() {

    private lateinit var binding: FragmentFarmDetailBinding
    private lateinit var farmDetailViewModel: FarmDetailViewModel
    private lateinit var bottomSheetDialog: CalendarBottomSheetDialog
    private var farmId: Int = 0
    private lateinit var phoneNumber: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_detail, container, false)
        farmDetailViewModel = ViewModelProvider(this)[FarmDetailViewModel::class.java]
        bottomSheetDialog = CalendarBottomSheetDialog()
        val farmImageAdapter = FarmImageAdapter()
        binding.farmDetailImage.adapter = farmImageAdapter
        farmId = arguments?.getInt("farmId") ?: 0

        farmDetailViewModel.getFarmDetail(farmId)

        binding.farmDetailToolbar.toolbarWithoutTitleBackButton.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setImageResource(R.drawable.back_vector_image_white)
            setOnClickListener { (activity as MainActivity).changeFragment(FarmFragment()) }
        }

        farmDetailViewModel.farmDetail.observe(viewLifecycleOwner) { detailRes ->
            val result = detailRes.result
            binding.farmDetail = result
            farmImageAdapter.setData(result)
            phoneNumber = result.farmer.PhoneNumber
            Log.e("FarmDetailResult","$result")
        }

        farmDetailViewModel.isSuccessReserve.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                val uri = Uri.parse("sms:$phoneNumber")
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.farmDetailRequestApplication.setOnClickListener {
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }

        bottomSheetDialog.setOnButtonClick(object : CalendarBottomSheetDialog.OnButtonClickListener {
            override fun buttonClick(firstDay: CalendarDay, lastDay: CalendarDay) {
                val email = UserPrefsStorage.email ?: ""
                Log.e("ButtonClick","$email,$farmId,${firstDay.date},${lastDay.date}")
                farmId
                val reserveRequestReq = ReserveRequestReq(
                    email = email,
                    farmId = farmId.toString(),
                    startDate = firstDay.date.toString(),
                    endDate = lastDay.date.toString()
                )
                farmDetailViewModel.postReserveRequest(reserveRequestReq)
            }
        })

    }

}