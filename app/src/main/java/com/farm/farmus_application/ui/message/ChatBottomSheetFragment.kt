package com.farm.farmus_application.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farm.farmus_application.databinding.FragmentBottomSheetChatBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChatBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetChatBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰 초기화 이벤트 수행
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}