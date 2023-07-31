package com.example.farmus_application.ui.farm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.databinding.FragmentFarmTab2Binding
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.ui.account.EnrollFarmerFragment
import com.example.farmus_application.utilities.JWTUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFarmListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFarmListFragment : Fragment() {

    private lateinit var binding: FragmentFarmTab2Binding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val jwtToken = UserPrefsStorage.accessToken
    private val email = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.email ?: ""
    // 농장주가 아니면 리스트 자체를 요청할 필요가 없다  (C: client, F: Farmer)
    private val role = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.role ?: ""

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
        binding = FragmentFarmTab2Binding.inflate(layoutInflater, container, false)
        Log.e("JWT_Token","$jwtToken")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //보유중인 농장 리사이클러뷰
//        val farmDataItems = mutableListOf<MyFarmDataModel>()
//        farmDataItems.add(MyFarmDataModel(R.drawable.farm_image_example, "위치", "농장 이름", "농장 크기"))
//        farmDataItems.add(MyFarmDataModel(R.drawable.farm_image_example, "위치", "농장 이름", "농장 크기"))
//        farmDataItems.add(MyFarmDataModel(R.drawable.farm_image_example, "위치", "농장 이름", "농장 크기"))
//        farmDataItems.add(MyFarmDataModel(R.drawable.farm_image_example, "위치", "농장 이름", "농장 크기"))
//
//        adapter = MyFarmRVAdapter() {
//            // farmDetail로 이동하는거 처럼 changeFarmDetailFragment함수 사용해야 할듯
//            (activity as MainActivity).changeFragment(FarmerFarmDetailFragment())
//        }
//        adapter.submitList(farmDataItems)
//        binding.rvTab2.adapter = adapter
//        binding.rvTab2.layoutManager = LinearLayoutManager(requireContext())
//
//        binding.floatingActionButton.setOnClickListener {
//            (activity as MainActivity).changeFragment(FirstFarmRegistrationFragment())
//        }
        viewVisibility(role)
        val myFarmAdapter = MyFarmRVAdapter() {
            // FarmerFarmDetailFragment로 이동
        }

        binding.rvTab2.apply {
            adapter = myFarmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.emptyMyFarmButton.setOnClickListener {
            (activity as MainActivity).changeFragmentAddToBackStack(EnrollFarmerFragment())
        }
    }

    private fun viewVisibility(role: String) {
        if (role == "C") {
            binding.emptyMyFarmText.visibility = View.VISIBLE
            binding.emptyMyFarmButton.visibility = View.VISIBLE
            binding.rvTab2.visibility = View.GONE
            binding.floatingActionButton.visibility = View.GONE
        } else {
            binding.emptyMyFarmText.visibility = View.GONE
            binding.emptyMyFarmButton.visibility = View.GONE
            binding.rvTab2.visibility = View.VISIBLE
            binding.floatingActionButton.visibility = View.VISIBLE
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFarmListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}