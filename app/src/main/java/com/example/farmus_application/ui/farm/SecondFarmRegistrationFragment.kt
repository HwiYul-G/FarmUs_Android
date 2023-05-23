package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmSecondRegistrationBinding

class SecondFarmRegistrationFragment: Fragment() {

    private lateinit var farmSecondRegistrationBinding: FragmentFarmSecondRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmSecondRegistrationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_second_registration, container, false)
        val view = farmSecondRegistrationBinding

        return view.root
    }

    // edit Text의 범위가 너무 작아서 클릭이 안됨 width를 최대로(match)로 늘리고 배열을 end로 하는 방법으로 해봐야할듯
}