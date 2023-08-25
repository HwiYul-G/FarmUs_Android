package com.farm.farmus_application.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.farm.farmus_application.databinding.FragmentEnrollFarmerBinding
import com.farm.farmus_application.ui.MainActivity
import com.farm.farmus_application.viewmodel.account.EnrollFarmerViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EnrollFarmerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnrollFarmerFragment : Fragment() {

    private lateinit var binding: FragmentEnrollFarmerBinding
    private lateinit var enrollFarmerViewModel: EnrollFarmerViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnrollFarmerBinding.inflate(layoutInflater, container, false)
        enrollFarmerViewModel = ViewModelProvider(this)[EnrollFarmerViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enrollFarmerViewModel.farmRegister.observe(viewLifecycleOwner) { response ->
            if (response) {
                Toast.makeText(requireContext(), "농장주 등록이 완료되었습니다.",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "농장주 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        //BottomNavigationView 숨기기
        (activity as MainActivity).hideBottomNavigation(true)

        //모두 동의합니다 체크 박스 기능
        binding.checkboxAgreeAll.setOnClickListener {
            if(binding.checkboxAgreeAll.isChecked) {
                binding.checkboxPrivacy.isChecked = true
                binding.checkboxTOS.isChecked = true
                binding.btnAgree.isSelected = true
            } else {
                binding.checkboxPrivacy.isChecked = false
                binding.checkboxTOS.isChecked = false
                binding.btnAgree.isSelected = false
            }
        }
        //이용약관 체크박스 클릭 이벤트
        binding.checkboxTOS.setOnClickListener {
            if(binding.checkboxTOS.isChecked){
                if(binding.checkboxPrivacy.isChecked) {
                    binding.checkboxAgreeAll.isChecked = true
                    binding.btnAgree.isSelected = true
                } else {
                    binding.btnAgree.isSelected = false
                }
            } else {
                binding.checkboxAgreeAll.isChecked = false
                binding.btnAgree.isSelected = false
            }
        }
        //개인정보 처리방침 체크박스 클릭이벤트
        binding.checkboxPrivacy.setOnClickListener {
            if(binding.checkboxPrivacy.isChecked){
                if(binding.checkboxTOS.isChecked) {
                    binding.checkboxAgreeAll.isChecked = true
                    binding.btnAgree.isSelected = true
                } else binding.btnAgree.isSelected = false
            } else{
                binding.checkboxAgreeAll.isChecked = false
                binding.btnAgree.isSelected = false
            }
        }

        binding.btnAgree.setOnClickListener {
            enrollFarmerViewModel.patchFarmRegister()
            (activity as MainActivity).changeFragment(CompleteEnrollFarmerFragment.newInstance("",""))
        }

        binding.toolBar.toolbarWithoutTitleBackButton.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction().remove(this@EnrollFarmerFragment).commit()
                popBackStack()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnrollFarmerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EnrollFarmerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}