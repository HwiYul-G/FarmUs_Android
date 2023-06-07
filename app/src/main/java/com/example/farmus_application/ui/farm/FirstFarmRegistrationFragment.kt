package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNext()

        (activity as MainActivity).hideBottomNavigation(true)
        binding.farmFirstRegistrationToolbar.toolbarWithoutTitleBackButton.setOnClickListener{
            (activity as MainActivity).changeFragment(FarmFragment())
        }

        binding.farmFirstRegistrationToolbarNextButton.setOnClickListener {
            (activity as MainActivity).changeFragment(SecondFarmRegistrationFragment())
        }

    }

    private fun checkNext() {
        binding.farmFirstRegistrationFarmTitleEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val titleEdit = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
                binding.farmFirstRegistrationToolbarNextButton.isEnabled = titleEdit.isNotEmpty() && introductionEdit.isNotEmpty()
            }
        })

        binding.farmFirstRegistrationFarmIntroductionEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit
                val introductionLength = introductionEdit.text.toString().length
                binding.introductionEditCount.text = introductionLength.toString()
                if (introductionLength > 150) {
                    introductionEdit.setText(introductionEdit.toString().substring(0, 150))
                    introductionEdit.setSelection(introductionLength)
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                val titleEdit = binding.farmFirstRegistrationFarmTitleEdit.text.toString()
                val introductionEdit = binding.farmFirstRegistrationFarmIntroductionEdit.text.toString()
                binding.farmFirstRegistrationToolbarNextButton.isEnabled = titleEdit.isNotEmpty() && introductionEdit.isNotEmpty()
            }
        })
    }
}