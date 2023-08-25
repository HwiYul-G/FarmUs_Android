package com.farm.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmModifyBinding

class FarmModifyFragment: Fragment() {

    private lateinit var farmModifyBinding: FragmentFarmModifyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        farmModifyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_farm_modify, container, false)
        val view = farmModifyBinding

        view.farmModifyToolbar.toolbarMainTitleText.text = "글 수정"
        view.farmModifyToolbar.toolbarMainTitleText.setTextColor(resources.getColor(R.color.text_2))

        return view.root
    }
}