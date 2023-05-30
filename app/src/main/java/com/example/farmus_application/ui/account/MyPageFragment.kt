package com.example.farmus_application.ui.account
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentMyPageBinding
import com.example.farmus_application.ui.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {

    private lateinit var myPageBinding: FragmentMyPageBinding

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
    ): View? {
        // Inflate the layout for this fragment
        myPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        val view = myPageBinding

        //bottomNavigationView 보이게
        (activity as MainActivity).hideBottomNavigation(false)

        //프로필 수정 버튼 누르면 ProfileSettingFragment로 이동
        view.btnProfileSetting.setOnClickListener{
            (activity as MainActivity).changeFragment(ProfileSettingFragment.newInstance("",""))
        }
        //설정 버튼 누르면 MyPageSettingFragment로 이동
        view.btnSetting.setOnClickListener {
            (activity as MainActivity).changeFragment(AccountSettingFragment.newInstance("",""))
        }

        view.listEnrollFarmer.setOnClickListener {
            (activity as MainActivity).changeFragment(EnrollFarmerFragment.newInstance("",""))
        }

        return view.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}