package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FarmDetailFragment: Fragment() {

    private lateinit var farmDetailBinding: FragmentFarmDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_detail, container, false)
        val view = farmDetailBinding

        farmDetailBinding.farmDetailRequestCalendar.setOnClickListener {
            val bottomSheetDialog = CalendarBottomSheetDialog()
            bottomSheetDialog.show(parentFragmentManager, "bottomSheetDialog")
        }

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}