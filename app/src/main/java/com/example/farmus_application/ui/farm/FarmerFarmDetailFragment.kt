package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmerFarmDetailBinding

class FarmerFarmDetailFragment: Fragment() {

    private lateinit var farmerFarmDetailBinding: FragmentFarmerFarmDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmerFarmDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farmer_farm_detail, container, false)
        val view = farmerFarmDetailBinding

        return view.root
    }
}