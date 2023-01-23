package com.example.farmus_application.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farmus_application.databinding.FragmentSignupSecondBinding

class SignupSecondFragment: Fragment(){
    private lateinit var viewBinding : FragmentSignupSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSignupSecondBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}