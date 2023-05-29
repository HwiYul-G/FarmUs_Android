package com.example.farmus_application.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.farmus_application.databinding.FragmentBottomSheetChatBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}