package com.farm.farmus_application.ui.account

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ProfileSettingValidationDialogFragment : DialogFragment(){

    // 유효성 검사가 틀린 원인을 bundle로 받아옴
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arguments = arguments
        val errorMessage = arguments?.getString("errorMessage")

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(errorMessage)
                .setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}