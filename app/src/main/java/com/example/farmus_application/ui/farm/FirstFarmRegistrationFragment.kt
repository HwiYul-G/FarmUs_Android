package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmFirstRegistrationBinding

class FirstFarmRegistrationFragment: Fragment() {

    private lateinit var farmFirstRegistrationBinding: FragmentFarmFirstRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmFirstRegistrationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_first_registration, container, false)
        val view = farmFirstRegistrationBinding


        return view.root
    }
}