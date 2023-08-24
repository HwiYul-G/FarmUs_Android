package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.DialogBottomSheetRequestBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RequestBottomSheetDialog: BottomSheetDialogFragment() {

    private lateinit var bottomSheetRequestBinding: DialogBottomSheetRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomSheetRequestBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_request, container, false)
        val view = bottomSheetRequestBinding

        return view.root
    }

}