package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmCompleteBinding

class FarmCompleteFragment: Fragment() {

    private lateinit var farmCompleteBinding: FragmentFarmCompleteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        farmCompleteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_complete, container, false)
        val view = farmCompleteBinding

        view.farmCompleteHomeButton.setOnClickListener {
            // Home 으로 이동
        }

        return view.root
    }
}