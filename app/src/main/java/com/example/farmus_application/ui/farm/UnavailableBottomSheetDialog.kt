package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.DialogBottomSheetUnavailableBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UnavailableBottomSheetDialog: BottomSheetDialogFragment() {

    private lateinit var binding: DialogBottomSheetUnavailableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottom_sheet_unavailable, container, false)

        return binding.root
    }
}