package com.farm.farmus_application.presentation.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmApplicationBinding

class FarmApplicationFragment: Fragment() {

    private lateinit var farmApplicationBinding: FragmentFarmApplicationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmApplicationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_application, container, false)
        val view = farmApplicationBinding

        view.farmApplicationToolbar.toolbarMainTitleText.text = "분양 신청"
        view.farmApplicationToolbar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        return view.root
    }
}