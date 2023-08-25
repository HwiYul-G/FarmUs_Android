package com.farm.farmus_application.ui.farm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.FragmentFarmBinding
import com.farm.farmus_application.ui.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FarmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FarmFragment : Fragment() {

    private lateinit var binding : FragmentFarmBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val tabTitleArray = arrayListOf(
        "분양받은 농장",
        "보유중인 농장"
    )
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
        binding = FragmentFarmBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //toolbar 이름 설정
        binding.toolbar.toolbarOnlyTitleText.text = "내 농장"
        binding.toolbar.toolbarOnlyTitleText.setTextColor(resources.getColor(R.color.text_first))
        (activity as MainActivity).hideBottomNavigation(false)

        binding.apply {
            farmViewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(farmTabLayout, farmViewpager) { tab, position ->
                tab.text = tabTitleArray[position]
            }.attach()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FarmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FarmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}