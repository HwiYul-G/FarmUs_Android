package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmSecondRegistrationBinding
import com.example.farmus_application.ui.MainActivity

class SecondFarmRegistrationFragment: Fragment() {

    private lateinit var binding: FragmentFarmSecondRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_second_registration, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.farmSecondRegistrationToolbar.toolbarWithoutTitleBackButton.setOnClickListener {
            (activity as MainActivity).changeFragment(FirstFarmRegistrationFragment())
        }
    }

}