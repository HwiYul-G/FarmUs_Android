package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmFirstRegistrationBinding
import com.example.farmus_application.ui.MainActivity

class FirstFarmRegistrationFragment: Fragment() {

    private lateinit var binding: FragmentFarmFirstRegistrationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_first_registration, container, false)
        val view = binding

        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).hideBottomNavigation(true)
        binding.farmFirstRegistrationToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            (activity as MainActivity).changeFragment(FarmFragment())
        }

        binding.farmFirstRegistrationToolbarNextButton.setOnClickListener {
            (activity as MainActivity).changeFragment(SecondFarmRegistrationFragment())
        }

    }
}